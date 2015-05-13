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
    static int reg = 1;
    static int ifNo = 0;

    static Stack<Integer> brstack = new Stack<Integer>();

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

    static void declare_i32(String id){
        main_text += "%"+id+" = alloca i32\n";
    }

    static void declare_double(String id){
        main_text += "%"+id+" = alloca double\n";
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

    static void sub_double(String val1, String val2){
        main_text += "%"+reg+" = fsub double "+val1+", "+val2+"\n";
        reg++;
    }

    static void div_i32(String val1, String val2){
        main_text += "%"+reg+" = sdiv i32 "+val2+", "+val1+"\n";
        reg++;
    }

    static void div_double(String val1, String val2){
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
        ifNo++;
        main_text += "br i1 %"+(reg-1)+", label %ok"+ ifNo +", label %fail"+ ifNo +"\n";
        main_text += "ok"+ ifNo +":\n";
        brstack.push(ifNo);
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

    static void gt(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp slt i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void lt(String id, String value){
        main_text += "%"+reg+" = load i32* %"+id+"\n";
        reg++;
        main_text += "%"+reg+" = icmp sgt i32 %"+(reg-1)+", "+value+"\n";
        reg++;
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
