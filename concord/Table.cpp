// Name:Wenbo Ye
// USC NetID:wenboye
// CSCI 455 PA5
// Fall 2019

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   hashSize = HASH_SIZE;
   hashTable = new ListType[hashSize];
   for(int i = 0; i < hashSize; i++){
      hashTable[i] = NULL;
   }
   numEntry = 0;
}


Table::Table(unsigned int hSize) {
   hashSize = hSize;
   hashTable = new ListType[hashSize];
   for(int i = 0; i < hashSize; i++){
      hashTable[i] = NULL;
   }
   numEntry = 0;
}

//look up the key. if exsits return value, else, return null
int * Table::lookup(const string &key) {
   unsigned int curCode = hashCode(key);
   if (listContains(hashTable[curCode], key)) {
     Node * cur = hashTable[curCode];
     for (; cur -> key != key; cur = cur -> next) {
     }
     return & (cur -> value);
   }
   else{
     return NULL;
   }
}

//remove the entry. if exsits return true, else, return false
bool Table::remove(const string &key) {
   unsigned int curCode = hashCode(key); 
   if (listRemove(hashTable[curCode], key)) {
      numEntry--;
      return true;
   }
   else {
      return false;
   }
}

//insert the entry. if entry has already exsited return true, else, return false
bool Table::insert(const string &key, int value) {   
   unsigned int curCode = hashCode(key); 
   if (listAdd(hashTable[curCode], key, value)) {
     numEntry++;
     return true;
   }
   else {
     return false;
   }
}

//return the number of entries
int Table::numEntries() const {
   return numEntry;      // dummy return value for stub
}

//print tables' information
void Table::printAll() const {
   for (int i = 0; i < hashSize; i++) {
     if (hashTable[i] != NULL) {
       printList(hashTable[i]);
     }
   }
}

//output the required information about hash table
void Table::hashStats(ostream &out) const {
   cout << "number of buckets: " << hashSize << endl;
   cout << "number of entries: " << numEntry << endl;
   cout << "number of non-empty buckets: " << numOfNonEmpty(hashTable) << endl;
   cout << "longest chain: " << longestChain(hashTable) << endl;
}


// add definitions for your private methods here

//return the numbers of entry
int Table::numOfNonEmpty(ListType * data) const {    
   int hasEntry = 0;
   for (int i = 0; i < hashSize; i++) {
      if (data[i] != NULL) {
         hasEntry++;
      }
   }
   return hasEntry;

}


//return the length of longest chain
int Table::longestChain(ListType * data) const {  
   int longestLen = 0;
   int size;
   for (int i = 0; i < hashSize; i++) {
      if (data[i] != NULL) {
         size = getSize(data[i]);
         if (size > longestLen){
            longestLen = size;
         }
      }
   }
   return longestLen;   
}
