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
class DataSet(dataset.DataSetInterface()):

    # Provide constructor, take array of arguments as args
    def __init__(self, args):
        self.args = args;


    # Adds date with name, id and content
    def __setitem__(self, name, id_content):
        

    # Add a DataSetItem
    def __iadd__(self, item):
        
    
    # Deletes a date by name
    def __delitem__(self, name):

    
    # Checks if a date + name combo exists
    def __contains__(self, name):


    # Retrieves a date by name
    def __getitem(self, name):


    # Returns dataset with content which both datasets have in common
    def __and__(self, dataset):


    # Returns content of both datasets as one 
    def __or__(self, dataset):


    # Iterates over everything inside this dataset based on iterate_sorted, iterate_reversed and iterate_key
    def __iter__(self):


    # Iterates like __iter__ but accepts a lambda function as filter
    def filtered_iterate(self, filter):


    # Returns amount of entries in dataset
    def __len__(self):