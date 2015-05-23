import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * Created by loku on 02.05.15.
 */
public class CodeGenerator {

    static String header_text = "";
    static String main_text = "";
    static String buffer = "";
    static int tmp = 1;
    static int main_tmp = 1;
    static int if_no = 0;

    static Stack<Integer> brstack = new Stack<Integer>();

    static void functionstart(String id){
        main_text += buffer;
        main_tmp = tmp;
        buffer = "define i32 @"+id+"() nounwind {\n";
        tmp = 1;
    }

    static void functionend(){
        buffer += "ret i32 %"+(tmp -1)+"\n";
        buffer += "}\n";
        header_text += buffer;
        buffer = "";
        tmp = main_tmp;
    }

    static void call(String id){
        buffer += "%"+ tmp +" = call i32 @"+id+"()\n";
        tmp++;
    }


    static void close_main(){
        main_text += buffer;
    }

    static void printf_i32(String id, String p){
        buffer += "%"+ tmp +" = load i32* "+p+id+"\n";
        tmp++;
        buffer += "%" + tmp +" = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpi, i32 0, i32 0), i32 %"+(tmp -1)+")\n";
        tmp++;
    }

    static void printf_double(String id, String p){
        buffer += "%"+ tmp +" = load double* "+p+id+"\n";
        tmp++;
        buffer += "%"+ tmp +" = call i32(i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpd, i32 0, i32 0), double %"+(tmp -1)+")\n";
        tmp++;
    }

    static void scanf_i32(String id){
        buffer += "%"+ tmp +" = call i32 (i8*, ...)* @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8]* @strs, i32 0, i32 0), i32* %"+id+")\n";
        tmp++;
    }

    static void scanf_double(String id){
        buffer += "%"+ tmp +" = call i32 (i8*, ...)* @__isoc99_scanf(i8* getelementptr inbounds ([4 x i8]* @strr, i32 0, i32 0), double* %"+id+")\n";
        tmp++;
    }

    static void declare_i32(String id, boolean local){
        if(local) {
            buffer += "%" + id + " = alloca i32\n";
        }
        else{
            header_text += "@"+id+" = global i32 0\n";
        }
    }

    static void declare_double(String id, boolean local){
        if(local) {
            buffer += "%" + id + " = alloca double\n";
        }
        else{
            header_text += "@"+id+" = global double 0\n";
        }
    }

    static void assign_i32(String id, String value, String p){
        buffer += "store i32 "+value+", i32* "+p+id+"\n";
    }

    static void assign_double(String id, String value, String p){
        buffer += "store double "+value+", double* "+p+id+"\n";
    }

    static void load_i32(String id){
        buffer += "%"+ tmp +" = load i32* %"+id+"\n";
        tmp++;
    }

    static void load_double(String id){
        buffer += "%"+ tmp +" = load double* %"+id+"\n";
        tmp++;
    }

    static void add_i32(String val1, String val2){
        buffer += "%"+ tmp +" = add i32 "+val1+", "+val2+"\n";
        tmp++;
    }

    static void add_double(String val1, String val2){
        buffer += "%"+ tmp +" = fadd double "+val1+", "+val2+"\n";
        tmp++;
    }

    static void mult_i32(String val1, String val2){
        buffer += "%"+ tmp +" = mul i32 "+val1+", "+val2+"\n";
        tmp++;
    }

    static void mult_double(String val1, String val2){
        buffer += "%"+ tmp +" = fmul double "+val1+", "+val2+"\n";
        tmp++;
    }

    static void sub_i32(String val1, String val2){
        buffer += "%"+ tmp +" = sub i32 "+val2+", "+val1+"\n";
        tmp++;
    }

    static void sub_double(String val2, String val1){
        buffer += "%"+ tmp +" = fsub double "+val1+", "+val2+"\n";
        tmp++;
    }

    static void div_i32(String val1, String val2){
        buffer += "%"+ tmp +" = sdiv i32 "+val2+", "+val1+"\n";
        tmp++;
    }

    static void div_double(String val2, String val1){
        buffer += "%"+ tmp +" = fdiv double "+val1+", "+val2+"\n";
        tmp++;
    }

    static void toDouble(String id){
        buffer += "%"+ tmp +" = sitofp i32 "+id+" to double\n";
        tmp++;
    }

    static void toInt(String id){
        buffer += "%"+ tmp +" = fptosi double "+id+" to i32\n";
        tmp++;
    }

    static void ifBegin(){
        if_no++;
        buffer += "br i1 %"+(tmp -1)+", label %ok"+ if_no +", label %fail"+ if_no +"\n";
        buffer += "ok"+ if_no +":\n";
        brstack.push(if_no);
    }

    static void ifEnd(){
        int b = brstack.pop();
        buffer += "br label %fail"+b+"\n";
        buffer += "fail"+b+":\n";
    }

    static void eq(String id, String value){
        buffer += "%"+ tmp +" = load i32* %"+id+"\n";
        tmp++;
        buffer += "%"+ tmp +" = icmp eq i32 %"+(tmp -1)+", "+value+"\n";
        tmp++;
    }

    static void lt(String id, String value){
        buffer += "%"+ tmp +" = load i32* %"+id+"\n";
        tmp++;
        buffer += "%"+ tmp +" = icmp slt i32 %"+(tmp -1)+", "+value+"\n";
        tmp++;
    }

    static void le(String id, String value){
        buffer += "%"+ tmp +" = load i32* %"+id+"\n";
        tmp++;
        buffer += "%"+ tmp +" = icmp sle i32 %"+(tmp -1)+", "+value+"\n";
        tmp++;
    }

    static void gt(String id, String value){
        buffer += "%"+ tmp +" = load i32* %"+id+"\n";
        tmp++;
        buffer += "%"+ tmp +" = icmp sgt i32 %"+(tmp -1)+", "+value+"\n";
        tmp++;
    }

    static void ge(String id, String value){
        buffer += "%"+ tmp +" = load i32* %"+id+"\n";
        tmp++;
        buffer += "%"+ tmp +" = icmp sge i32 %"+(tmp -1)+", "+value+"\n";
        tmp++;
    }

    static void loopBegin(String n){
        declare_i32(Integer.toString(tmp),true);
        int counter = tmp;
        tmp++;
        assign_i32(Integer.toString(counter), "0","%");
        if_no++;
        buffer += "br label %cond"+ if_no +"\n";
        buffer += "cond"+ if_no +":\n";

        load_i32(Integer.toString(counter));
        add_i32("%" + (tmp - 1), "1");
        assign_i32(Integer.toString(counter), "%" + (tmp - 1),"%");

        buffer += "%"+ tmp +" = icmp slt i32 %"+(tmp -2)+", "+n+"\n";
        tmp++;

        buffer += "br i1 %"+(tmp -1)+", label %true"+ if_no +", label %false"+ if_no +"\n";
        buffer += "true"+ if_no +":\n";
        brstack.push(if_no);
    }

    static void loopVBegin(String id){
        declare_i32(Integer.toString(tmp),false);
        int counter = tmp;
        tmp++;
        assign_i32(Integer.toString(counter), "0","%");
        if_no++;
        buffer += "br label %cond"+ if_no +"\n";
        buffer += "cond"+ if_no +":\n";

        load_i32(Integer.toString(counter));
        add_i32("%" + (tmp - 1), "1");
        assign_i32(Integer.toString(counter), "%" + (tmp - 1),"%");
        load_i32(id);

        buffer += "%"+ tmp +" = icmp sle i32 %"+(tmp -2)+", "+"%" + (tmp - 1)+"\n";
        tmp++;

        buffer += "br i1 %"+(tmp -1)+", label %true"+ if_no +", label %false"+ if_no +"\n";
        buffer += "true"+ if_no +":\n";
        brstack.push(if_no);
    }

    static void loopEnd(){
        int b = brstack.pop();
        buffer += "br label %cond"+b+"\n";
        buffer += "false"+b+":\n";
    }

    static String generate(){
        close_main();
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
