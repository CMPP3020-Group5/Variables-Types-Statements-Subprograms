from input_helper import readNonEmpty, readDate, readGender, readFloat, readInt
from student import Student, StudentRepository

studentRepository = StudentRepository()

def main():

  try:
      
      while True:
        print()
        print("=== Student Enrollment Management System ===")
        print("1. Add student")
        print("2. Modify student")
        print("3. Remove student")
        print("4. Display all students")
        print("5. Parameter-passing demo")
        print("6. Exit")
        
        while True:
          choice = input("Choose an option (1-6): ").strip()
          if choice is None or choice.strip() == "":
            print("[ERROR]: value cannot be empty.")
            continue
          if choice in ["1", "2", "3", "4", "5", "6"]:
            break
          print("[ERROR]: please enter a whole number between 1 and 6.")
          
        match choice:
          case "1":
            addStudent()
          case "2":
            modifyStudent()
          case "3":
            removeStudent()
          case "4":
            printAll()
          case "5":
            parameterPassingDemo()
          case "6":
            return
          case _:
            print("[ERROR]: please enter a whole number between 1 and 6.")
  except EOFError:
    print("\nInput ended.")
  finally:
    print("Goodbye.")



def addStudent():
  # verifies that the user input is valid before creating a new student object
  firstName = readNonEmpty("First name: ")
  lastName = readNonEmpty("Last name: ")
  birthDate = readDate("Date of birth (yyyy-MM-dd): ")
  gender = readGender("Gender (M/F/X): ")
  previousGpa = readFloat("Previous GPA (0.0-4.0): ", 0.0, 4.0)
  currentSemester = readInt("Current semester (1-4): ", 1, 4)
  program = readNonEmpty("Program: ")
  numberOfCourses = readInt("Number of courses: ", 0, 10)

  try:
    studentRepository.insert(Student(firstName, lastName, birthDate, gender, previousGpa, currentSemester, program, numberOfCourses))
    print("Student added.")
  except ValueError as e:
    print(f"[ERROR] Error adding student: {e}")

#Changes a field of a student
def modifyStudent():
  #python doesn't have a max integer value so the Java max value of 2147483647 is used instead
  studentId = readInt("Student ID: ",1, 2147483647)
  student = studentRepository.findStudentById(studentId)
  if student is None:
    print("[ERROR]: student not found.")
    return
  print(student)
  print("")
  print("0. Cancel")
  print("1. First name")
  print("2. Last name")
  print("3. Date of birth")
  print("4. Gender")
  print("5. Previous GPA")
  print("6. Current semester")
  print("7. Program")
  print("8. Number of courses")

#working on a copy of the student
  updatingStudent = student
  choice = readInt("Choose a field to change (0-8): ", 0, 8)
  match choice:
    case 0:
      print("Modify cancelled.")
      return
    case 1:
      updatingStudent.firstName = readNonEmpty("First name: ")
    case 2:
      updatingStudent.lastName = readNonEmpty("Last name: ")
    case 3:
      updatingStudent.birthDate = readDate("Date of birth (yyyy-MM-dd): ")
    case 4:
      updatingStudent.gender = readGender("Gender (M/F/X): ")
    case 5:
      updatingStudent.previousGpa = readFloat("Previous GPA (0.0-4.0): ", 0.0, 4.0)
    case 6:
      updatingStudent.currentSemester = readInt("Current semester (1-4): ", 1, 4)
    case 7:
      updatingStudent.program = readNonEmpty("Program: ")
    case 8:
      updatingStudent.numberOfCourses = readInt("Number of courses: ", 0, 10)
    case _:
      print()

  try:
    studentRepository.modify(studentId, updatingStudent)
    print("Student updated.")
  except ValueError as e:
    print(f"[ERROR]Error modifying student: {e}")

def removeStudent():
  studentId = readInt("Student ID: ",1, 2147483647)
  if studentRepository.remove(studentId):
    print("Student removed.")
  else:
    print("[ERROR]: student not found.")
  pass

def printAll():
  if not studentRepository.studentList:
    print("No students found.")
    return
  for student in studentRepository.studentList:
    print(student)

def parameterPassingDemo():
  print("\n=== Parameter-passing demo ===")
  demoPrimitive()
  demoChangeField()
  demoNewObject()
  demoString()

def demoPrimitive():
  print("\n[1] Primitive (int): pass by value")
  courses = 5
  print(f"Before: courses = {courses}")
  changeNumber(courses)
  print(f"After:  courses = {courses}")
  print("-> The method got a copy of the number, so the original did not change.")

def changeNumber(courses):
  courses = 6
  print(f"Inside method: courses = {courses}")

def demoChangeField():
  print("\n[2] Object (Student): change a field")
  student = Student("Jason", "Wu", "2000-01-01", "M", 3.6, 1, "Software Development", 5)
  print(f"Before: {student.firstName} {student.lastName}, GPA {student.previousGpa:.2f}")
  changeGpa(student)
  print(f"After:  {student.firstName} {student.lastName}, GPA {student.previousGpa:.2f}")
  print("-> The method got a copy of the reference, but it points to the same object, so the change is visible.")

def changeGpa(student):
  student.previousGpa = 4.00
  print(f"Inside method: GPA = {student.previousGpa:.2f}")

def demoNewObject():
  print("\n[3] Object (Student): assign a new object")
  student = Student("Jason", "Wu", "2000-01-01", "M", 3.6, 1, "Software Development", 5)
  print(f"Before: {student.firstName} {student.lastName}")
  replaceStudent(student)
  print(f"After:  {student.firstName} {student.lastName}")
  print("-> The method changed only its own copy of the reference. The caller's reference did not change.")

def replaceStudent(student):
  student = Student("Amy", "Lee", "2001-05-20", "F", 3.9, 2, "Software Development", 4)
  print(f"Inside method: {student.firstName} {student.lastName}") 

def demoString():
  print("\n[4] String: immutable")
  program = "Software Development"
  print(f"Before: program = {program}")
  changeProgram(program)
  print(f"After:  program = {program}")
  print("-> A String cannot be changed. The method made a new String.")

def changeProgram(program):
  program = program + " (Year 2)"
  print(f"Inside method: program = {program}")





if __name__ == "__main__":
  main()