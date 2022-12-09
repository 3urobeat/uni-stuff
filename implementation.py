"""
Hier eine Klasse `DataSet` implementieren.

Die Klasse muss eine Unterklasse von `dataset.DataSetInterface` sein
und alle dort geforderten Methoden implementieren.
Bei den Methoden von `DataSetInterface` stehe auch weitere Infos
zur benoetigen Funktionalitaet!

Die hier implementierte Klasse wird spaeter mittels `from implementation import DataSet`
geladen und mit Daten befüllt. Anschließend werden die Daten ausgelesen und überprüft!

Alle drei Dateien liegen im gleichen Ordner.

"""	

# Importe
import dataset

# Klasse: DataSet
class DataSet(dataset.DataSetInterface):

    # Provide constructor, take array of arguments as args
    def __init__(self, *args):
        super().__init__(*args)

        self.items = {}

    # Add date with name, id and content
    def __setitem__(self, name, id_content):
        self.items[name] = dataset.DataSetItem(name, id_content[0], id_content[1])

    # Add a DataSetItem
    def __iadd__(self, item):
        self.items[item.name] = item

        return self # Important so that += works
    
    # Delete an item by name
    def __delitem__(self, name):
        self.items.pop(name)
    
    # Check if a DataSetItem is in DataSet
    def __contains__(self, name):
        return bool(self.items.get(name))

    # Retrieve a date by name
    def __getitem__(self, name):
        return self.items.get(name)

    # Return dataset with content which both datasets have in common
    def __and__(self, dataset):
        newSet = DataSet() # Create new set

        # Iterate over dataset and items and push every DataSet with matching 'name' values into newSet
        for e in dataset:
            for f in self.items.values():
                if e.name == f.name:
                    newSet += f # Use item from self for newSet

        return newSet

    # Return content of both datasets as one 
    def __or__(self, dataset):
        newSet = DataSet() # Create new set

        # Push all DataSetItems from self
        for e in self.items.values():
            newSet += e

        # Push all DataSetItems from dataset, overwriting duplicates
        for e in dataset:
            newSet += e

        return newSet

    # Iterate over everything inside this dataset based on iterate_sorted, iterate_reversed and iterate_key
    def __iter__(self):
        res = self.items # result dict to apply sort to if self.iterate_... is enabled

        # Apply sorts if desired
        if self.iterate_sorted:
            if self.iterate_key == self.ITERATE_SORT_BY_ID: # Sort by ID
                res = {key: val for val, key in sorted(self.items.items(), key = lambda e: e[1][0])} # e: Access ID
            else: # Sort by name
                res = {key: val for key, val in sorted(self.items.items(), key = lambda e: e[0])} # e: Access name

        if self.iterate_reversed:
            res = dict(reversed(list(res.items())))

        # Return iterable
        return iter(res.values())

    # Iterate like __iter__ but accepts a lambda function as filter
    def filtered_iterate(self, filter):
        print("test")

    # Return amount of entries in dataset
    def __len__(self):
        return len(self.items.keys())