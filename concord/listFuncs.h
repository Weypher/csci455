// Name:
// USC NetID:
// CSCI 455 PA5
// Fall 2019


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.


// Adds one new Node with given string and value to the front of the list.
// if node existed, return false and do nothing, else, add in front and return true.
bool listAdd(ListType & list, std::string tgt, int value);

// Removes the node with the given string in the list.
// if target existed, remove the key and value and return true, otherwise return false and do nothing.
bool listRemove(ListType & list, std::string tgt);

// Checks whether the list contains the target. If contains, return true; ow, return false.
bool listContains(ListType & list, std::string tgt);

// update the lists with the target. if target exist, update the new value and return true, or return false and do nothing.
bool listUpdate(ListType & list, std::string tgt, int newValue);

// Prints all nodes in the list.
void printList(ListType list);

// calculate the size of list
int getSize(ListType list);


// keep the following line at the end of the file
#endif
