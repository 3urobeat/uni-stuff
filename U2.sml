(* Run file by starting 'smlnj' in the same folder, then loading the file using 'use "U2.sml";' *)

(* -------- Task 5: Implement 4 described helper functions -------- *)

(* Remove all elements in l matching a *)
fun remAll(a, l) =
    List.filter(fn x => x <> a) l; (* Check if element is not a and keep it *)
    
(* Return element by index from list *)
fun ith(l, i) = List.nth(l, i); (* nth() of List does exactly what ith is supposed to do *)

(* Check for scattered sublist (unfinished) *)
fun isScatteredSublist([], bl) = true;   (* If first list is empty, the second is always a sublist of it *)
    | isScatteredSublist(_, []) = false; (* Random input is never a sublist of an empty list *)
    | isScatteredSublist(xl, y::yl) =
        let 
            fun delete (_, nil) = nil
                | delete (0, _::xs) = xs
                | delete (i, x::xs) = x::del(i - 1, xs);

            fun isPrefix(nil, xl) = true
                | isPrefix( _, nil) = false
                | isPrefix(x::xl, y::yl) = (x = y andalso isPrefix(xl,yl)) orelse (delete(yl, List.nth(yl, y)) andalso isPrefix(xl, yl)); (* Somehow call a delete element function here to remove the element so we can check if the next match is "scattered" somewhere else, but we weren't sure how to as a bool is expected *)
        in 
            isPrefix(xl,y::yl) orelse isScatteredSublist(xl,yl)
        end;

(* Remove all duplicate elements from list *)
fun unique([]) = []
    | unique(x::xs) = x::unique(List.filter (fn y => y <> x) xs);