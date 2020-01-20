// Name:
// USC NetID:
// CSCI 455 PA5
// Fall 2019


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

// add nodes in front of the list. if contains return false, ow, return true.
bool listAdd(ListType & list, std::string tgt, int value) {
   if (listContains(list, tgt)) {
      return false;
   } else {
      list = new Node(tgt, value, list);
      return true;
   }
}

// Removes the give node. if node exsits remove and return true, ow, return false
bool listRemove(ListType & list, std::string tgt) {
   Node * cur = list;   // pointer which points to the current node ready to check.
   Node * pre = NULL;   // pointer which points to the previous node of the current
   while (cur != NULL) {       
      if (cur -> key == tgt) {
         if (pre != NULL) {
            pre -> next = cur -> next;
            cur = NULL;
         }
         else {
            list = list -> next;
         }
         return true;
      }  
      pre = cur;
      cur = cur -> next;
   }
   return false;
}

// Checks whether the list contains the target. if node has been exsited return true, ow, false.
bool listContains(ListType & list, std::string tgt) {
   Node * cur = list;
   while (cur != NULL) {
      if (cur -> key == tgt) {
         return true;
      }
      cur = cur -> next;
   }
   return false;
}

// Updates the value of given target. If target exists in the list, update its value and return
// true; ow, do nothing and return false.
bool listUpdate(ListType & list, std::string tgt, int newValue) {
   Node * cur = list;
   while (cur != NULL) {
      // If target exists, updates its value.
      if (cur -> key == tgt) {
         cur -> value = newValue;
         return true;
      }
      cur = cur -> next;
   }
   return false;
}

// Prints all nodes in the list.
void printList(ListType list) {
   // If list has no nodes, prints <empty>.
   if (list == NULL) {
      cout << "The list is empty now." << endl;
   }
   Node * cur = list;
   while (cur != NULL) {
      cout << cur -> key << "->" << cur -> value << endl;
      cur = cur -> next;
   }
}

// Gets the size of the list.
int getSize(ListType list) {
   int size = 0;
   for (Node * cur = list; cur != NULL; cur = cur -> next) {
      size++;
   }
   return size;
}