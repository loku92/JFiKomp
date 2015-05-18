import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * Created by loku on 02.05.15.
 */
public class ByteCodeGenerator {

    static String header_text = "";
    static String main_text = "";
    static String buffer = "";
    static int reg = 1;
    static int fun_reg = 1;
    static int if_no = 0;

    static Stack<Integer> brstack = new Stack<Integer>();

    static void functionstart(String id){
        main_text += buffer;
        main_tmp = tmp;
        buffer = "define i32 @"+id+"() nounwind {\n";
        tmp = 1;
    }

    static void functionend(){
        buffer += "ret i32 %"+(tmp-1)+"\n";
        buffer += "}\n";
        header_text += buffer;
        buffer = "";
        tmp = main_tmp;
    }

    static void call(String id){
        buffer += "%"+tmp+" = call i32 @"+id+"()\n";
        tmp++;
    }


    static void close_main(){
        main_text += buffer;
    }

    static void printf_i32(String id){
        main_text += "%"+ reg +" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+ reg +" = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpi, i32 0, i32 0), i32 %"+(reg-1)+")\n";
        reg++;
    }

    static void printf_double(String id){
        main_text += "%"+ reg +" = load double* %"+id+"\n";
        reg++;
        main_text += "%"+ reg +" = call i32(i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpd, i32 0, i32 0), double %"+(reg-1)+")\n";
        reg++;
    }

    static void scanf_i32(String id){
        main_text += "%"+ reg +" = call i32 (i8*, ...)* @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8]* @strs, i32 0, i32 0), i32* %"+id+")\n";
        reg++;
    }

    static void scanf_double(String id){
        main_text += "%"+ reg +" = call i32 (i8*, ...)* @__isoc99_scanf(i8* getelementptr inbounds ([4 x i8]* @strr, i32 0, i32 0), double* %"+id+")\n";
        reg++;
    }

    static void declare_i32(String id, boolean local){
        if(local) {
            main_text += "%" + id + " = alloca i32\n";
        }
        else{
            header_text += "@"+id+" = global i32 0\n";
        }
    }

    static void declare_double(String id, boolean local){
        if(local) {
            main_text += "%" + id + " = alloca double\n";
        }
        else{
            header_text += "@"+id+" = global double 0\n";
        }
    }

    static void assign_i32(String id, String value){
        main_text += "store i32 "+value+", i32* %"+id+"\n";
    }

    static void assign_double(String id, String value){
        main_text += "store double "+value+", double* %"+id+"\n";
    }

    static void load_i32(String id){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
    }

    static void load_double(String id){
        main_text += "%"+reg+" = load double* %"+id+"\n";
        reg++;
    }

    static void add_i32(String val1, String val2){
        main_text += "%"+reg+" = add i32 "+val1+", "+val2+"\n";
        reg++;
    }

    static void add_double(String val1, String val2){
        main_text += "%"+reg+" = fadd double "+val1+", "+val2+"\n";
        reg++;
    }

    static void mult_i32(String val1, String val2){
        main_text += "%"+reg+" = mul i32 "+val1+", "+val2+"\n";
        reg++;
    }

    static void mult_double(String val1, String val2){
        main_text += "%"+reg+" = fmul double "+val1+", "+val2+"\n";
        reg++;
    }

    static void sub_i32(String val1, String val2){
        main_text += "%"+reg+" = sub i32 "+val2+", "+val1+"\n";
        reg++;
    }

    static void sub_double(String val2, String val1){
        main_text += "%"+reg+" = fsub double "+val1+", "+val2+"\n";
        reg++;
    }

    static void div_i32(String val1, String val2){
        main_text += "%"+reg+" = sdiv i32 "+val2+", "+val1+"\n";
        reg++;
    }

    static void div_double(String val2, String val1){
        main_text += "%"+reg+" = fdiv double "+val1+", "+val2+"\n";
        reg++;
    }

    static void toDouble(String id){
        main_text += "%"+reg+" = sitofp i32 "+id+" to double\n";
        reg++;
    }

    static void toInt(String id){
        main_text += "%"+reg+" = fptosi double "+id+" to i32\n";
        reg++;
    }

    static void ifBegin(){
        if_no++;
        main_text += "br i1 %"+(reg-1)+", label %ok"+ if_no +", label %fail"+ if_no +"\n";
        main_text += "ok"+ if_no +":\n";
        brstack.push(if_no);
    }

    static void ifEnd(){
        int b = brstack.pop();
        main_text += "br label %fail"+b+"\n";
        main_text += "fail"+b+":\n";
    }

    static void eq(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp eq i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void lt(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp slt i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void le(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp sle i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void gt(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp sgt i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void ge(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp sge i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void loopBegin(String n){
        declare_i32(Integer.toString(reg),false);
        int counter = reg;
        reg++;
        assign_i32(Integer.toString(counter), "0");
        if_no++;
        main_text += "br label %cond"+ if_no +"\n";
        main_text += "cond"+ if_no +":\n";

        load_i32(Integer.toString(counter));
        add_i32("%" + (reg - 1), "1");
        assign_i32(Integer.toString(counter), "%" + (reg - 1));

        main_text += "%"+reg+" = icmp slt i32 %"+(reg-2)+", "+n+"\n";
        reg++;

        main_text += "br i1 %"+(reg-1)+", label %true"+ if_no +", label %false"+ if_no +"\n";
        main_text += "true"+ if_no +":\n";
        brstack.push(if_no);
    }

    static void loopVBegin(String id){
        declare_i32(Integer.toString(reg),false);
        int counter = reg;
        reg++;
        assign_i32(Integer.toString(counter), "0");
        if_no++;
        main_text += "br label %cond"+ if_no +"\n";
        main_text += "cond"+ if_no +":\n";

        load_i32(Integer.toString(counter));
        add_i32("%" + (reg - 1), "1");
        assign_i32(Integer.toString(counter), "%" + (reg - 1));
        load_i32(id);

        main_text += "%"+reg+" = icmp sle i32 %"+(reg-2)+", "+"%" + (reg - 1)+"\n";
        reg++;

        main_text += "br i1 %"+(reg-1)+", label %true"+ if_no +", label %false"+ if_no +"\n";
        main_text += "true"+ if_no +":\n";
        brstack.push(if_no);
    }

    static void loopEnd(){
        int b = brstack.pop();
        main_text += "br label %cond"+b+"\n";
        main_text += "false"+b+":\n";
    }

    static String generate(){
        String text = "";
        text += "declare i32 @printf(i8*, ...)\n";
        text += "declare i32 @__isoc99_scanf(i8*, ...)\n";
        text += "@strpi = constant [4 x i8] c\"%d\\0A\\00\"\n";
        text += "@strpd = constant [4 x i8] c\"%f\\0A\\00\"\n";
        text += "@strs = constant [3 x i8] c\"%d\\00\"\n";
        text += "@strr = constant [4 x i8] c\"%lf\\00\"\n";
        text += header_text;
        text += "define i32 @main() nounwind{\n";
        text += main_text;
        text += "ret i32 0 }\n";
        File f = new File(Main.fileName);
        try {
            PrintWriter pw = new PrintWriter(f);
            pw.println(text);
            pw.close();
        }
        catch (FileNotFoundException e){
            System.err.println("Failed to create a file.");
        }

        return text;
    }
}
