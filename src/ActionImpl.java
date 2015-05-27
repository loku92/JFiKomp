import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by loku on 02.05.15.
 */

//TODO : fun int or real,assign fun
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

public class ActionImpl extends GramatykaBaseListener {

    HashMap<String, VarType> global = new HashMap<String, VarType>();
    HashMap<String, VarType> local = new HashMap<String, VarType>();
    HashMap<String, VarType> funs = new HashMap<>();
    Stack<Value> stack = new Stack<Value>();
    String function;
    static VarType last = VarType.INT;
    boolean isLocal = false;
    boolean expr = false;
    boolean iff = false;
    boolean isDef = false;
    boolean call = false;

    @Override
    public void exitProg(@NotNull GramatykaParser.ProgContext ctx) {
        System.out.println(CodeGenerator.generate());
    }


    @Override
    public void exitAssign(@NotNull GramatykaParser.AssignContext ctx) {
        String ID = ctx.ID().getText();
        Value v = stack.pop();
        String p = "@";
        last = v.type;
        if (isLocal) {//
            if (v.type == VarType.INT) {
                if (!local.containsKey(ID) && !global.containsKey(ID)) {
                    local.put(ID, v.type);
                    p="%";
                    CodeGenerator.declare_i32(ID, isLocal);
                }
                else if(local.containsKey(ID)){
                    p="%";
                }else{
                    p="@";
                }
                CodeGenerator.assign_i32(ID, v.name, p);
            } else if (v.type == VarType.REAL) {
                if (!local.containsKey(ID) && !global.containsKey(ID)) {
                    local.put(ID, v.type);
                    p="%";
                    CodeGenerator.declare_double(ID, isLocal);
                }
                else if(local.containsKey(ID)){
                    p="%";
                }
                else{
                    p="@";
                }
                CodeGenerator.assign_double(ID, v.name, p);
            } else {
                raiseError(String.valueOf(ctx.getStart().getLine()));
            }//
        } else {
            if (v.type == VarType.INT) {
                if (!global.containsKey(ID)) {
                    global.put(ID, v.type);
                    CodeGenerator.declare_i32(ID, isLocal);
                }
                CodeGenerator.assign_i32(ID, v.name, "@");
            } else if (v.type == VarType.REAL) {
                if (!global.containsKey(ID)) {
                    global.put(ID, v.type);
                    CodeGenerator.declare_double(ID, isLocal);
                }
                CodeGenerator.assign_double(ID, v.name, "@");
            } else {
                raiseError(String.valueOf(ctx.getStart().getLine()));
            }
        }
    }


    @Override
    public void enterRetCall(@NotNull GramatykaParser.RetCallContext ctx) {
        call = true;
    }

    @Override
    public void exitRetCall(@NotNull GramatykaParser.RetCallContext ctx) {
        String ID = ctx.ID().getText();
        String p = "@";
        Value v = stack.pop();
        if (v.type == VarType.INT ) {
            if(local.containsKey(ID)) {
                p="%";
            }
            else if(global.containsKey(ID)){
                p="@";
            }
            else{
                global.put(ID,VarType.INT);
                CodeGenerator.declare_i32(ID,false);
            }
            CodeGenerator.assign_i32(ID, v.name, p);
        } else {
            if(local.containsKey(ID)) {
                p="%";
            }
            else if(global.containsKey(ID)){
                p="@";
            }
            else{
                global.put(ID,VarType.REAL);
                CodeGenerator.declare_double(ID, false);
            }
            CodeGenerator.assign_double(ID, v.name, p);
        }
    }

    @Override
    public void exitAdd(@NotNull GramatykaParser.AddContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(v2.name.matches("^@[0-9]+$")){
            v2.name = v2.name.replaceAll("@","%");
        }
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                CodeGenerator.add_i32(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                CodeGenerator.add_double(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.REAL));
            }
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Adding undeclared value.");
        }
        expr = false;
    }

    @Override
    public void exitMult(@NotNull GramatykaParser.MultContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(v2.name.matches("^@[0-9]+$")){
            v2.name = v2.name.replaceAll("@","%");
        }
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                CodeGenerator.mult_i32(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                CodeGenerator.mult_double(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.REAL));
            }
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Mult undeclared value.");
        }
        expr = false;
    }

    @Override
    public void exitDiv(@NotNull GramatykaParser.DivContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(v2.name.matches("^@[0-9]+$")){
            v2.name = v2.name.replaceAll("@","%");
        }
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                CodeGenerator.div_i32(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                CodeGenerator.div_double(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.REAL));
            }
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Div undeclared value.");
        }
        expr = false;
    }


    @Override
    public void exitSub(@NotNull GramatykaParser.SubContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(v2.name.matches("^@[0-9]+$")){
            v2.name = v2.name.replaceAll("@","%");
        }
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                CodeGenerator.sub_i32(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                CodeGenerator.sub_double(v1.name, v2.name);
                stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.REAL));
            }
        } else {
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
            print(type, ID, "%");
        } else if (gtype != null) {
            print(gtype, ID, "@");
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Print undeclared value.");
        }
    }

    private void print(VarType type, String ID, String p) {
        if (type == VarType.INT) {
            CodeGenerator.printf_i32(ID, p);
        } else {
            CodeGenerator.printf_double(ID, p);
        }
    }

    @Override
    public void exitRead(@NotNull GramatykaParser.ReadContext ctx) {
        String ID = ctx.ID().getText();
        String p = "@";
        if (isLocal) {
            p = "%";
            if (!local.containsKey(ID)) {
                if (global.containsKey(ID)) {
                    p = "@";
                    isLocal = false;
                    //CodeGenerator.declare_i32(ID, isLocal);
                    isLocal = true;
                } else {
                    local.put(ID, VarType.INT);
                    CodeGenerator.declare_i32(ID, isLocal);
                }
            }
        } else {
            if (!global.containsKey(ID)) {
                global.put(ID, VarType.INT);
                CodeGenerator.declare_i32(ID, isLocal);
            }
        }
        CodeGenerator.scanf_i32(ID, p);

    }

    @Override
    public void enterReadReal(@NotNull GramatykaParser.ReadRealContext ctx) {
        String ID = ctx.ID().getText();
        String p = "@";
        if (isLocal) {
            p = "%";
            if (!local.containsKey(ID)) {
                if (global.containsKey(ID)) {
                    p = "@";
                    isLocal = false;
                    CodeGenerator.declare_double(ID, isLocal);
                    isLocal = true;
                } else {
                    local.put(ID, VarType.INT);
                    CodeGenerator.declare_double(ID, isLocal);
                }
            }
        } else {
            if (!global.containsKey(ID)) {
                global.put(ID, VarType.INT);
                CodeGenerator.declare_double(ID, isLocal);
            }
        }
        CodeGenerator.scanf_double(ID, p);
    }

    @Override
    public void exitToint(@NotNull GramatykaParser.TointContext ctx) {
        Value v = stack.pop();
        CodeGenerator.toInt(v.name);
        stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.INT));
        expr = false;
    }

    @Override
    public void exitToreal(@NotNull GramatykaParser.TorealContext ctx) {
        Value v = stack.pop();
        CodeGenerator.toDouble(v.name);
        stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.REAL));
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
            if (isLocal) {
                if (t != null) {
                    load(t,ctx.ID().getText(),"%");
                } else if (gt != null) {
                    load(gt, ctx.ID().getText(),"@");
                } else {
                    raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value in expression");
                }
            } else {
                load(gt, ctx.ID().getText(),"@");
            }
        }
    }

    private void load(VarType t, String ID, String p) {
        if (t == VarType.INT) {
            CodeGenerator.load_i32(ID,p);
            stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.INT));
        } else if (t == VarType.REAL) {
            CodeGenerator.load_double(ID,p);
            stack.push(new Value("%" + (CodeGenerator.reg - 1), VarType.REAL));
        }
    }

    @Override
    public void enterFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        isLocal = true;
        isDef = true;
    }

    @Override
    public void exitCall(@NotNull GramatykaParser.CallContext ctx) {
        String Id = ctx.ID().getText();
        if (isDef) {
            function = Id;
            funs.put(Id, VarType.INT);
            CodeGenerator.functionstart(Id);
            isDef = false;
        } else if (call) {
            if(!funs.containsKey(Id)){
                raiseError(". Function " + Id + "() doesn't exist.");
            }
            else {
                CodeGenerator.call(Id,funs.get(Id));
                stack.push(new Value("%" + (CodeGenerator.reg - 1), funs.get(Id)));
                call = false;
            }
        } else {
            if (funs.containsKey(Id)) {
                CodeGenerator.call(Id,funs.get(Id));
            } else {
                raiseError(ctx.getStart().getLine() + " Called fun wasn't declared");
            }
        }
    }

    @Override
    public void exitFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        isLocal = false;
        VarType v = VarType.REAL;
        if(last == VarType.INT) {
            v = VarType.INT;
        }
        funs.put(function,v);
        local.clear();
        function = "";
        CodeGenerator.functionend();
    }

    @Override
    public void exitCondint(@NotNull GramatykaParser.CondintContext ctx) {
        CodeGenerator.loopBegin(ctx.INT().getText());
    }

    @Override
    public void exitCondvalue(@NotNull GramatykaParser.CondvalueContext ctx) {
        String Id = ctx.ID().getText();
        if (local.containsKey(Id)) {
            if (local.get(ctx.ID().getText()) == VarType.INT) {
                CodeGenerator.loopVBegin(Id);
            } else {
                raiseError(ctx.getStart().getLine() + " Using real value in loop.");
            }
        } else if (global.containsKey(Id)) {
            if (global.get(Id) == VarType.INT) {
                CodeGenerator.loopVBegin(Id);
            } else {
                raiseError(ctx.getStart().getLine() + " Using real value in loop.");
            }
        } else {
            raiseError(ctx.getStart().getLine() + " Using undeclared value in loop.");
        }
    }

    @Override
    public void exitLop(@NotNull GramatykaParser.LopContext ctx) {
        CodeGenerator.loopEnd();
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
        CodeGenerator.ifBegin();

    }

    @Override
    public void exitInif(@NotNull GramatykaParser.InifContext ctx) {
        CodeGenerator.ifEnd();
    }

    @Override
    public void exitLt(@NotNull GramatykaParser.LtContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if (local.containsKey(ID)) {
            CodeGenerator.lt(ID, INT, "%");
        } else if (global.containsKey(ID)) {
            CodeGenerator.lt(ID, INT, "@");
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
        }
    }

    @Override
    public void exitLe(@NotNull GramatykaParser.LeContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if (local.containsKey(ID)) {
            CodeGenerator.le(ID, INT, "%");
        } else if (global.containsKey(ID)) {
            CodeGenerator.le(ID, INT, "@");
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
        }
    }

    @Override
    public void exitGt(@NotNull GramatykaParser.GtContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if (local.containsKey(ID)) {
            CodeGenerator.gt(ID, INT, "%");
        } else if (global.containsKey(ID)) {
            CodeGenerator.gt(ID, INT, "@");
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
        }
    }

    @Override
    public void exitGe(@NotNull GramatykaParser.GeContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if (local.containsKey(ID)) {
            CodeGenerator.ge(ID, INT, "%");
        } else if (global.containsKey(ID)) {
            CodeGenerator.ge(ID, INT, "@");
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
        }
    }

    @Override
    public void exitEq(@NotNull GramatykaParser.EqContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if (local.containsKey(ID)) {
            CodeGenerator.eq(ID, INT, "%");
        } else if (global.containsKey(ID)) {
            CodeGenerator.eq(ID, INT, "@");
        } else {
            raiseError(String.valueOf(ctx.getStart().getLine()) + " Using undeclared value.");
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

    public void raiseError(String text) {
        System.err.println("Error in line:" + text);
        System.exit(-1);
    }
}
