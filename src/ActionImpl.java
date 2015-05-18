import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by loku on 02.05.15.
 */

//TODO : load, fix value range picker, fun int or real
enum VarType {
    INT, REAL
}

class Value {
    public String name;
    public VarType type;

    public Value(String name, VarType type) {
        this.name = name;
        this.type = type;
    }
}

public class ActionImpl extends GramatykaBaseListener  {

    HashMap<String, VarType> global = new HashMap<String, VarType>();
    HashMap<String, VarType> local = new HashMap<String, VarType>();
    HashSet<String> funs = new HashSet<>();
    Stack<Value> stack = new Stack<Value>();
    boolean isLocal = false;
    boolean expr = false;
    boolean iff = false;
    boolean isDef = false;

    @Override
    public void exitProg(@NotNull GramatykaParser.ProgContext ctx) {
        System.out.println(ByteCodeGenerator.generate());
    }

    @Override
    public void exitAssign(@NotNull GramatykaParser.AssignContext ctx) {
        if(isLocal) {
            String ID = ctx.ID().getText();
            Value v = stack.pop();
            if (v.type == VarType.INT) {
                if (!local.containsKey(ID)) {
                    local.put(ID, v.type);
                    ByteCodeGenerator.declare_i32(ID, isLocal);
                }
                ByteCodeGenerator.assign_i32(ID, v.name);
            } else if (v.type == VarType.REAL) {
                if (!local.containsKey(ID)) {
                    local.put(ID, v.type);
                    ByteCodeGenerator.declare_double(ID, isLocal);
                }
                ByteCodeGenerator.assign_double(ID, v.name);
            } else {
                raiseError(String.valueOf(ctx.getStart().getLine()));
            }
        }
        else{
            String ID = ctx.ID().getText();
            Value v = stack.pop();
            if (v.type == VarType.INT) {
                if (!global.containsKey(ID)) {
                    global.put(ID, v.type);
                    ByteCodeGenerator.declare_i32(ID, isLocal);
                }
                ByteCodeGenerator.assign_i32(ID, v.name);
            } else if (v.type == VarType.REAL) {
                if (!global.containsKey(ID)) {
                    global.put(ID, v.type);
                    ByteCodeGenerator.declare_double(ID, isLocal);
                }
                ByteCodeGenerator.assign_double(ID, v.name);
            } else {
                raiseError(String.valueOf(ctx.getStart().getLine()));
            }
        }

    }

    @Override
    public void exitAdd(@NotNull GramatykaParser.AddContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                ByteCodeGenerator.add_i32(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                ByteCodeGenerator.add_double(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
            }
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Adding undeclared value.");
        }
        expr = false;
    }

    @Override
    public void exitMult(@NotNull GramatykaParser.MultContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                ByteCodeGenerator.mult_i32(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                ByteCodeGenerator.mult_double(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
            }
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine())+ " Mult undeclared value.");
        }
        expr = false;
    }

    @Override
    public void exitDiv(@NotNull GramatykaParser.DivContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                ByteCodeGenerator.div_i32(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                ByteCodeGenerator.div_double(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
            }
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine())+ " Div undeclared value.");
        }
        expr = false;
    }


    @Override
    public void exitSub(@NotNull GramatykaParser.SubContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                ByteCodeGenerator.sub_i32(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                ByteCodeGenerator.sub_double(v1.name, v2.name);
                stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
            }
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Sub undeclared value.");
        }
        expr = false;
    }

    @Override
    public void exitPrint(@NotNull GramatykaParser.PrintContext ctx) {
        String ID = ctx.ID().getText();
        VarType type = local.get(ID);
        VarType gtype = global.get(ID);
        if (type != null) {
            print(type,ID);
        }
        else if (gtype != null){
            print(type,ID);
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Print undeclared value.");
        }
    }

    private void print(VarType type, String ID){
        if (type == VarType.INT) {
            ByteCodeGenerator.printf_i32(ID);
        }
        if (type == VarType.REAL) {
            ByteCodeGenerator.printf_double(ID);
        }
    }

    @Override
    public void exitRead(@NotNull GramatykaParser.ReadContext ctx) {
        String ID = ctx.ID().getText();
        if(isLocal) {
            if (!local.containsKey(ID)) {
                local.put(ID, VarType.INT);
                ByteCodeGenerator.declare_i32(ID, isLocal);
            }
        }
        else{
            if (!global.containsKey(ID)) {
                global.put(ID, VarType.INT);
                ByteCodeGenerator.declare_i32(ID, isLocal);
            }
        }
        ByteCodeGenerator.scanf_i32(ID);

    }

    @Override
    public void enterReadReal(@NotNull GramatykaParser.ReadRealContext ctx) {
        String ID = ctx.ID().getText();
        if (isLocal) {
            if (!local.containsKey(ID)) {
                local.put(ID, VarType.REAL);
                ByteCodeGenerator.declare_double(ID, isLocal);
            }
        }
        else{
            if (!global.containsKey(ID)) {
                global.put(ID, VarType.REAL);
                ByteCodeGenerator.declare_double(ID, isLocal);
            }
        }
        ByteCodeGenerator.scanf_double(ID);
    }

    @Override
    public void exitToint(@NotNull GramatykaParser.TointContext ctx) {
        Value v = stack.pop();
        ByteCodeGenerator.toInt(v.name);
        stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.INT));
        expr = false;
    }

    @Override
    public void exitToreal(@NotNull GramatykaParser.TorealContext ctx) {
        Value v = stack.pop();
        ByteCodeGenerator.toDouble(v.name);
        stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
        expr = false;
    }

    @Override
    public void exitInt(@NotNull GramatykaParser.IntContext ctx) {
        stack.push(new Value(ctx.INT().getText(), VarType.INT));
    }

    @Override
    public void exitReal(@NotNull GramatykaParser.RealContext ctx) {
        stack.push(new Value(ctx.REAL().getText(), VarType.REAL));
    }

    @Override
    public void exitId(@NotNull GramatykaParser.IdContext ctx) {
        if (expr) {
            VarType t = local.get(ctx.ID().getText());
            VarType gt = global.get(ctx.ID().getText());
            if(isLocal) {
                if (t != null) {
                    if (t == VarType.INT) {
                        ByteCodeGenerator.load_i32(ctx.ID().getText());
                        stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.INT));
                    } else if (t == VarType.REAL) {
                        ByteCodeGenerator.load_double(ctx.ID().getText());
                        stack.push(new Value("%" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
                    }
                }
                else if(gt != null){
                    load(gt, ctx.ID().getText());
                }
            }
            else{
                load(gt, ctx.ID().getText());
            }
        }
    }
    //TODO: load
    private void load(VarType t,String ID){
        if (t == VarType.INT) {
            ByteCodeGenerator.load_i32(ID);
            stack.push(new Value("@" + (ByteCodeGenerator.tmp - 1), VarType.INT));
        } else if (t == VarType.REAL) {
            ByteCodeGenerator.load_double(ID);
            stack.push(new Value("@" + (ByteCodeGenerator.tmp - 1), VarType.REAL));
        }
    }

    @Override
    public void enterFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        isLocal = true;
        isDef = true;
    }

    @Override
    public void exitCall(@NotNull GramatykaParser.CallContext ctx) {
        if(isDef){
            funs.add(ctx.ID().getText());
            isDef = false;
        }
        else{
            String Id = ctx.ID().getText();
            if(funs.contains(Id)){
                ByteCodeGenerator.call(Id);
            }
            else{
                raiseError(ctx.getStart().getLine() + " Called fun wasn't declared");
            }
        }
    }

    @Override
    public void exitFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        isLocal = false;
    }

    @Override
    public void exitCondint(@NotNull GramatykaParser.CondintContext ctx) {
        ByteCodeGenerator.loopBegin(ctx.INT().getText());
    }

    @Override
    public void exitCondvalue(@NotNull GramatykaParser.CondvalueContext ctx) {
        if(local.containsKey(ctx.ID().getText())) {
            if(local.get(ctx.ID().getText()) == VarType.INT){
                ByteCodeGenerator.loopVBegin(ctx.ID().getText());
            }
            else{
                raiseError(ctx.getStart().getLine() + " Using real value in loop.");
            }
        }
        else{
            raiseError(ctx.getStart().getLine() + " Using undeclared value in loop.");
        }
    }

    @Override
    public void exitLop(@NotNull GramatykaParser.LopContext ctx) {
        ByteCodeGenerator.loopEnd();
    }

    @Override
    public void enterCond(@NotNull GramatykaParser.CondContext ctx) {
        iff = true;
    }

    @Override
    public void exitCond(@NotNull GramatykaParser.CondContext ctx) {
        iff = false;
    }

    @Override
    public void enterInif(@NotNull GramatykaParser.InifContext ctx) {
        ByteCodeGenerator.ifBegin();

    }

    @Override
    public void exitInif(@NotNull GramatykaParser.InifContext ctx) {
        ByteCodeGenerator.ifEnd();
    }

    @Override
    public void exitLt(@NotNull GramatykaParser.LtContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( local.containsKey(ID) ) {
            ByteCodeGenerator.lt(ID, INT);
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
        }
    }

    @Override
    public void exitLe(@NotNull GramatykaParser.LeContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( local.containsKey(ID) ) {
            ByteCodeGenerator.le(ID, INT);
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
        }
    }

    @Override
    public void exitGt(@NotNull GramatykaParser.GtContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( local.containsKey(ID) ) {
            ByteCodeGenerator.gt(ID, INT);
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine())+ " Using undeclared value.");
        }
    }

    @Override
    public void exitGe(@NotNull GramatykaParser.GeContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( local.containsKey(ID) ) {
            ByteCodeGenerator.ge(ID, INT);
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine())+ " Using undeclared value.");
        }
    }

    @Override
    public void exitEq(@NotNull GramatykaParser.EqContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( local.containsKey(ID) ) {
            ByteCodeGenerator.eq(ID, INT);
        }
        else{
            raiseError(String.valueOf(ctx.getStart().getLine())+ " Using undeclared value.");
        }
    }

    @Override
    public void enterMult(@NotNull GramatykaParser.MultContext ctx) {
        expr = true;
    }

    @Override
    public void enterPar(@NotNull GramatykaParser.ParContext ctx) {
        expr = true;
        super.enterPar(ctx);
    }

    @Override
    public void enterSub(@NotNull GramatykaParser.SubContext ctx) {
        expr = true;
        super.enterSub(ctx);
    }

    @Override
    public void enterValuue(@NotNull GramatykaParser.ValuueContext ctx) {
        super.enterValuue(ctx);
        expr = true;
    }

    @Override
    public void enterToreal(@NotNull GramatykaParser.TorealContext ctx) {
        expr = true;
        super.enterToreal(ctx);
    }

    @Override
    public void enterToint(@NotNull GramatykaParser.TointContext ctx) {
        expr = true;
        super.enterToint(ctx);
    }

    @Override
    public void enterDiv(@NotNull GramatykaParser.DivContext ctx) {
        expr = true;
        super.enterDiv(ctx);
    }

    @Override
    public void enterAdd(@NotNull GramatykaParser.AddContext ctx) {
        super.enterAdd(ctx);
        expr = true;
    }

    @Override
    public void exitValuue(@NotNull GramatykaParser.ValuueContext ctx) {
        expr = false;
        super.exitValuue(ctx);
    }

    @Override
    public void exitPar(@NotNull GramatykaParser.ParContext ctx) {
        expr = false;
        super.exitPar(ctx);
    }

    public void raiseError(String text){
        System.err.println("Error in line:" + text);
        System.exit(-1);
    }
}
