(* Run file by starting 'smlnj' in the same folder, then loading the file using 'use "U1.sml";' *)


(* -------- Task 1: Calculate entrance fee for a park with a simple function -------- *)

(* Parameters *)
val erm = true;
val dauer = 1;
val dschungel = false;
val gebtag = false;

fun calc() =
    let
        val price = 19.50 (* Default price *)

        (* Calculate the 3 first values here and the 4th below so that price gets modified by *each* call and in is not empty *)
        val price = if erm then price - 5.0 else price          (* -5€ if child *)
        val price = if dauer < 120 then price - 4.0 else price  (* -4€ if visit time <2 hours *)
        val price = if dschungel then price + 1.50 else price   (* +1,50€ if jungle night *)
    in
        if gebtag then price / 2.0 else price (* -50% of total if today is visitor's birthday *)
    end


(* -------- Task 2: Convert if statement to bool -------- *)

(* Parameters *)
val x = ~2; (* -2 is written as ~2 because SML otherwise expects another number to subtract 2 from *)
val y = 7;
val z = 4;

(* If statement written as bool *)
val res : bool = x < 0 orelse (x > y andalso not (x <= z));

(* Original if statement to compare results with *)
if x < 0 then true
    else if x > y then if x <= z then false
                                else true
                else false


(* -------- Task 3a: Implement function to round to 2 decimal places -------- *)
fun decimalPlaces2(x:real):real =
    let 
        val m = Math.pow(10.0, real 2)
    in 
        real(round(x * m)) / m
    end


(* -------- Task 3b -------- *)
fun isLeapYear(x) =
    x mod (if x mod 100 = 0 then 400 else 4) = 0


(* -------- Task 3c -------- *)
fun crossSum(0) = 0
    | crossSum(n) = n mod 10 + crossSum(n div 10)

val testInput = [(0), (20), (1234)]
val () = print (String.concatWith " " (map (Int.toString o crossSum) testInput) ^ "\n");


(* -------- Task 3d -------- *)
fun isPalindrome(p) =
    let 
        val str = explode p 
    in
        str = rev str
    end;


(* -------- Task 4: Write an isPrime() function -------- *)
fun isPrime(n:int):bool =
    if n = 2 then true                              (* Instantly return true if n is 2 *)
        else if n < 2 orelse n mod 2 = 0 then false (* Instantly return false if n < 2 or if n is even *)
        else 
            let
                fun trialDevisionLoop(j:int) = 
                    if j * j > n then true
                        else if n mod j = 0 then false
                        else trialDevisionLoop(j + 2)   (* Call recursively again *)
                in trialDevisionLoop(3)

                (* Explanation:
                If n < 9 and n isn't divisible by 2, then it's prime
                If n = 9 then it's not prime
                If n > 9, compare n to (k+2)^2, in this case 25 *)
            end