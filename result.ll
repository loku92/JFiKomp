declare i32 @printf(i8*, ...)
declare i32 @__isoc99_scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strr = constant [4 x i8] c"%lf\00"
@z = global i32 0
@y = global i32 0
@5 = global i32 0
define i32 @test() nounwind {
%1 = load i32* @z
%2 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpi, i32 0, i32 0), i32 %1)
store i32 4, i32* @z
%lokalna = alloca i32
store i32 3, i32* %lokalna
%3 = load i32* @z
%4 = icmp sge i32 %3, 0
br i1 %4, label %ok1, label %fail1
ok1:
store i32 0, i32* %5
br label %cond2
cond2:
%6 = load i32* %5
%7 = add i32 %6, 1
store i32 %7, i32* %5
%8 = load i32* %z
%9 = icmp sle i32 %7, %8
br i1 %9, label %true2, label %false2
true2:
%10 = load i32* @z
%11 = add i32 1, %10
store i32 %11, i32* @z
%12 = load i32* @z
%13 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpi, i32 0, i32 0), i32 %12)
br label %cond2
false2:
br label %fail1
fail1:
ret i32 %13
}
@lokalna = global i32 0
define i32 @retInt() nounwind {
%1 = mul i32 3, 1111
%b = alloca i32
store i32 %1, i32* %b
ret i32 %1
}
define double @retReal() nounwind {
%1 = sitofp i32 2 to double
%2 = fmul double %1, 15111.99
%b = alloca double
store double %2, double* %b
ret double %2
}
@g = global i32 0
@zmienna = global double 0.0
define i32 @main() nounwind{
store i32 6, i32* @z
store i32 3, i32* @y
store i32 6, i32* @lokalna
%1 = call i32 @test()
%2 = load i32* @lokalna
%3 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpi, i32 0, i32 0), i32 %2)
%4 = call i32 @retInt()
store i32 %4, i32* @g
%5 = call double @retReal()
store double %5, double* @zmienna
%6 = load i32* @g
%7 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpi, i32 0, i32 0), i32 %6)
%8 = alloca i32
store i32 0, i32* %8
br label %cond3
cond3:
%9 = load i32* %8
%10 = add i32 %9, 1
store i32 %10, i32* %8
%11 = icmp slt i32 %9, 2
br i1 %11, label %true3, label %false3
true3:
%12 = load double* @zmienna
%13 = call i32(i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @strpd, i32 0, i32 0), double %12)
br label %cond3
false3:
ret i32 0 }

