"""
	Beispieldatei, die das DataSet importiert, benutzt und evaluiert.

	Achtung, die Evaluation im Moodle hat einen weiteren Testfall und ein
	paar Schutzfunktionen, die eine Manipulation der Dateien `dataset.py`
	und `main.py` verhindern.
"""

import inspect

# Fail and Pass
def code_fail():
	print("CodeIsNotValid")
	print("\t Check failed in line", inspect.getframeinfo(inspect.stack()[1][0]).lineno) 
	exit()

def code_pass():
	print("CodeIsValid")

# Klassen laden
from dataset import DataSetItem
from implementation import DataSet

# Klasse pruefen
if not inspect.isclass(DataSet):
	code_fail()

# Attribute pruefen
d1 = DataSet()
if not all(hasattr(d1, a) for a in [
		"iterate_sorted", "iterate_reversed", "iterate_key",
		"ITERATE_SORT_BY_NAME", "ITERATE_SORT_BY_ID"
	]):
		code_fail()

# Einfuegen prüfen
d2_items = []
d2 = DataSet()
for i in range(20):
	item = DataSetItem(chr(i+65), 20-i, chr(i+97))
	d2_items.append(item)
	d2 += item 

if len(d2) != 20:
	code_fail()
if d2["A"].content != "a":
	code_fail()

d2["Z"] = (0, "zz")
if len(d2) != 21:
	code_fail()
if isinstance(d2["Z"], DataSetItem) and d2["Z"].id == 0:
	d2_items.append(d2["Z"])
else:
	code_fail()

# Iteration
for item, check in zip(d2, d2_items):
	if item != check:
		code_fail()

d2.set_iteration(sort=False)
for item, check in zip(d2, d2_items):
	if item != check:
		code_fail()

d2.set_iteration(sort=True, reverse=True)
d2_items.reverse()
for item, check in zip(d2, d2_items):
	if item != check:
		code_fail()


d4 = DataSet()
d4.set_iteration(sort=False)
d4 += DataSetItem("A", 1, "a")
d4 += DataSetItem("C", 3, "c")
d4 += DataSetItem("B", 2, "b")
d4 += DataSetItem("D", 4, "d")

d5 = DataSet()
d5.set_iteration(sort=False)
d5 += DataSetItem("C", 5, "c")
d5 += DataSetItem("E", 7, "e")
d5 += DataSetItem("D", 6, "d")
d5 += DataSetItem("F", 8, "f")
d5 += DataSetItem("G", 9, "g")

# Beinhaltet?
if not "G" in d5:
	code_fail()
if "G" in d4:
	code_fail()

# Loeschen
del d5["G"]
if "G" in d5:
	code_fail()
for i, l in zip(d5, ["c", "e", "d", "f"]):
	if i.content != l:
		code_fail()

# Schnitt 
d_intersect = d4 & d5 
if len(d_intersect) != 2:
	code_fail()

d_intersect.set_iteration(sort=True)
for i, l in zip(d_intersect, [3, 4]):
	if i.id != l:
		code_fail()

# Vereinigung
d_union = d4 | d5 
if len(d_union) != 6:
	code_fail()

d_union.set_iteration(sort=True)
for i, l in zip(d_union, [1, 2, 5, 6, 7, 8]):
	if i.id != l:
		code_fail()

code_pass()