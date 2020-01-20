// Name:  Wenbo Ye   
// USC NetID:wenboye
// CSCI 455 PA5
// Fall 2019

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

void gradeInsert(Table * &grades, string name, int score);
void change(Table * &grades, string name, int score);
void gradeLookup(Table * &grades, string name);
void gradeRemove(Table * &grades, string name);
void print(Table * &grades);
void size(Table * &grades);
void gradeStats(Table * &grades);
void help();
void selectFunc(Table * & grades);


int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   cout << "Please input \"help\" after the following \"cmd>\" if first time." << endl;
   selectFunc(grades);

   return 0;
}


// this is helper method to point to the other functions due to the input
void selectFunc(Table * &grades) {
       
   bool isDone = true;
   string input, name;
   int score;
   int num;

   while (isDone) {

      cout << "cmd> ";

      cin >> input;

      if (input == "insert") {
         num = 1; 
         cin >> name;
         cin >> score;
      } else if (input == "change") {
         num = 2; 
         cin >> name; 
         cin >> score;
      } else if (input == "lookup") {
         num = 3;
         cin >> name;
      } else if (input == "remove") {
         num = 4; 
         cin >> name;
      } else if (input == "print") {
         num = 5;
      } else if (input == "size") {
         num = 6;
      } else if (input == "stats") {
         num = 7;
      } else if (input == "help") {
         num = 8;
      } else if (input == "quit") {
         num = 9;
      } else {
         num = 10;
      }

      switch (num) {
         case 1: gradeInsert(grades, name, score); break;
         case 2: change(grades, name, score); break;
         case 3: gradeLookup(grades, name); break;
         case 4: gradeRemove(grades, name); break;
         case 5: print(grades); break;
         case 6: size(grades); break;
         case 7: gradeStats(grades); break;
         case 8: help(); break;
         case 9: isDone = false; break;
         default: cout << "ERROR: invalid command" << endl; break;
      }

   }
}

//insert the grade
void gradeInsert(Table * &grades, string name, int score) {
   if (grades -> insert(name, score)) {
      cout << "Inserted." << endl;
   } else {
      cout << "Failed: " << name << " has been existed." << endl;
      cout << "This student's score is " << score << endl;
   }
}  

//change the target garde's value, if exsit, change; ow do nothing  
void change(Table * &grades, string name, int score) {
   int * value = grades -> lookup(name);
   int pre = 0;

   if (value == NULL){
      cout << "Change failed: " << name << " does not exist." << endl;
   } else {
      pre = *value;
      *value = score;

      cout << "Change successfully." << endl;
      cout << name << " exists and the previous score is " << pre << endl;
   }
}

// search the key and related value
void gradeLookup(Table * &grades, string name) {
   int *res = grades -> lookup(name);
   if (res == NULL) {
      cout << name << "cannot be found." << endl;
   } else {
      cout << name << "exists and score is: " << *res << endl;
   }
}

//delete the target if exists
void gradeRemove(Table * &grades, string name) {
   if (grades -> remove(name)) {
      cout << "removed" << endl;
   } else {
      cout << "Remove failed: " << name << " does not exist." << endl;
   }
}

//print all
void print(Table * &grades) {
   grades -> printAll();
}
   
//get size of the grade
void size(Table * &grades) {
   cout << "The number of students is " << grades -> numEntries() << endl;
}
   
//get all information about the lists
void gradeStats(Table * &grades) {
   grades -> hashStats(cout);
}
  
//print the helper informations
void help(){
    cout << "-------------COMMAND SUMMARY-------------" << endl;
    cout << "-- insert name score" << endl;
    cout << "-- change name newscore" << endl;
    cout << "-- lookup name" << endl;
    cout << "-- remove name" << endl;
    cout << "-- print" << endl;
    cout << "-- size" << endl;
    cout << "-- stats " << endl;
    cout << "-- help" << endl;
    cout << "-- quit" << endl;
    cout << "-------------------END-------------------" << endl;
}
