# 🔊 Banker's Algorithm 

This project implements Banker's Algorithm,<b> a deadlock avoidance algorithm </b> used in operating systems. The algorithm ensures safe resource allocation by checking whether a process's resource request can be satisfied without leading to deadlock.

### 📎 Features 

✅ Reads process data from an input file

✅ Calculates the Need Matrix dynamically

✅ Calculates the Available Matix dynamically

✅ Checks system safety using the Safe Sequence

✅ Detects deadlocks if no process can proceed

✅ Displays step-by-step allocation updates

### 🗂️ Files

- Main.java - Core implementation of Banker's Algorithm
- test.java - Test program with an alternative dataset
- deadlock.txt / data1.txt - Input files defining resources and processes 

### 📝 Input File Format

```txt
   Allocation       Max          Total Resources
   A  B  C  D      A  B  C  D      A  B  C  D
P0  1  0  0  2     4  2  1  2      16  14  9  12
P1  4  2  1  3     5  2  3  4
P2  3  4  2  4     9  8  5  7
P3  2  3  3  1     8  4  6  6
P4  3  3  1  0     10  9  3  4
```
### 🎯 How It Works

- Reads process and resource data from the txt file
- Computes the Need Matrix:
    - Need = Max - Allocation
    - Available = Allocation - Total Resources
- Iteratively checks for a Safe Sequence:
- If a process’s needs can be met, it executes and releases its resources.
- Otherwise, it waits.
- If no process can proceed, a deadlock is detected.
- Prints the Safe Sequence or Deadlock Warning

### 🖨️ Output Format

- Print Available Matrix and Need Matrix
- Print the updated Available Matrix and Updated Need Matrix after each process
- Finally, a safe sequence. If detect the Deadlock, show deadlock warning and in which process deadlock happened.
### 🚨 Example Output

```
Available Matrix : 
3 2 2 2 
Need Matrix : 
[3, 2, 1, 0]
[1, 0, 2, 1]
[6, 4, 3, 3]
[6, 1, 3, 5]
[7, 6, 2, 4]
------------
Updated Need Matrix : 
[0, 0, 0, 0]
[1, 0, 2, 1]
[6, 4, 3, 3]
[6, 1, 3, 5]
[7, 6, 2, 4]
|
|more updated need matrix
|
Updated Need Matrix : 
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
------------
New Available Matrix : 
16 14 9 12 
Safe Sequence[P0, P1, P2, P3, P4]
```
### 💻 Technologies Used

- Java 23.0.1 Implementation
- File I/O for reading input file

### 🤝 Contributing
Feel free to fork the repo, improve the implementation, and submit pull requests!



