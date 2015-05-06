import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by loku on 02.05.15.
 */
enum VarType {
    INT, REAL, VAR
}

class Value {
    public String name;
    public VarType type;

    public Value(String name, VarType type) {
        this.name = name;
        this.type = type;
    }
}

public class LLVMactions extends GramatykaBaseListener {

    HashMap<String, VarType> global = new HashMap<String, VarType>();
    HashMap<String, VarType> local = new HashMap<String, VarType>();
    HashSet<String> declared = new HashSet<String>();
    Stack<Value> stack = new Stack<Value>();
    boolean isLocal = false;
    boolean isDeclaration = false;
    boolean expr2 = false;

    @Override
    public void exitProg(@NotNull GramatykaParser.ProgContext ctx) {
        System.out.println(LLVMGenerator.generate());
    }

    @Override
    public void exitAssign(@NotNull GramatykaParser.AssignContext ctx) {
        String ID = ctx.ID().getText();
        Value v = stack.pop();
        if (v.type == VarType.INT) {
            if (!local.containsKey(ID)) {
                local.put(ID, v.type);
                LLVMGenerator.declare_i32(ID);
            }
            LLVMGenerator.assign_i32(ID, v.name);
        }
        if (v.type == VarType.REAL) {
            if (!local.containsKey(ID)) {
                local.put(ID, v.type);
                LLVMGenerator.declare_double(ID);
            }
            LLVMGenerator.assign_double(ID, v.name);
        }

    }

    @Override
    public void exitAdd(@NotNull GramatykaParser.AddContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                LLVMGenerator.add_i32(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                LLVMGenerator.add_double(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
            }
        }
    }

    @Override
    public void exitMult(@NotNull GramatykaParser.MultContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                LLVMGenerator.mult_i32(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                LLVMGenerator.mult_double(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
            }
        }
    }

    @Override
    public void exitDiv(@NotNull GramatykaParser.DivContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                LLVMGenerator.div_i32(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                LLVMGenerator.div_double(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
            }
        }
    }


    @Override
    public void exitSub(@NotNull GramatykaParser.SubContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if (v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                LLVMGenerator.sub_i32(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                LLVMGenerator.sub_double(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
            }
        }
    }

    @Override
    public void exitPrint(@NotNull GramatykaParser.PrintContext ctx) {
        String ID = ctx.ID().getText();
        VarType type = local.get(ID);
        if (type != null) {
            if (type == VarType.INT) {
                LLVMGenerator.printf_i32(ID);
            }
            if (type == VarType.REAL) {
                LLVMGenerator.printf_double(ID);
            }
        }
    }

    @Override
    public void exitRead(@NotNull GramatykaParser.ReadContext ctx) {
        String ID = ctx.ID().getText();
        if(!local.containsKey(ID)){
            local.put(ID, VarType.INT);
            LLVMGenerator.declare_i32(ID);
        }
        LLVMGenerator.scanf_i32(ID);

    }


    @Override
    public void exitToint(@NotNull GramatykaParser.TointContext ctx) {
        Value v = stack.pop();
        LLVMGenerator.toInt(v.name);
        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
    }

    @Override
    public void exitToreal(@NotNull GramatykaParser.TorealContext ctx) {
        Value v = stack.pop();
        LLVMGenerator.toDouble(v.name);
        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
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
        if (expr2) {
            VarType t = local.get(ctx.ID().getText());
            if(t == VarType.INT) {
                LLVMGenerator.load_i32(ctx.ID().getText());
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
            }
            else if(t == VarType.REAL) {
                LLVMGenerator.load_double(ctx.ID().getText());
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
            }
        }
    }

    @Override
    public void enterFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        isLocal = true;
        isDeclaration = true;
    }

    @Override
    public void exitFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        isDeclaration = false;
        isLocal = false;
    }

    @Override
    public void enterSingle1(@NotNull GramatykaParser.Single1Context ctx) {
        expr2 = true;
    }

    @Override
    public void exitSingle1(@NotNull GramatykaParser.Single1Context ctx) {
        expr2 = false;
    }
}
