from input_helper import readNonEmpty, readDate, readGender, readFloat, readInt
from student import Student, StudentRepository

studentRepository = StudentRepository()

def main():

  try:
      
      while True:
        print("\n")
        print("===Student Enrollment Management System===")
        print("1. Add student")
        print("2. Modify student")
        print("3. Remove student")
        print("4. Display all students")
        print("5. Parameter-passing demo")
        print("6. Exit")
        choice = input("Choose an option (1-6): ")
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
            print("Exiting the program.")
            return
          case _:
            print("Invalid choice. Please enter a number between 1 and 6.")
  except ValueError:
    print("Invalid input. Please enter a number.")
  finally:
      print("Goodbye.")



def addStudent():
  # verifies that the user input is valid before creating a new student object
  firstName = readNonEmpty("First name: ")
  lastName = readNonEmpty("Last name: ")
  birthDate = readDate("Birth date (YYYY-MM-DD): ")
  gender = readGender("Gender (M/F/X): ")
  previousGpa = readFloat("Previous GPA (0.0 - 4.0): ", 0.0, 4.0)
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
  #python doesn't have a max integer value so the lastStudentID is used as the max value
  studentId = readInt("Student ID: ",1, studentRepository.lastStudentID)
  student = studentRepository.findStudentById(studentId)
  if student is None:
    print("[ERROR]: student not found")
    return
  print(student)
  print("\n")
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
  choice = readInt("Choose an option (0-8): ", 0, 8)
  match choice:
    case 0:
      print("Modify cancelled.")
      return
    case 1:
      updatingStudent.firstName = readNonEmpty("First name: ")
    case 2:
      updatingStudent.lastName = readNonEmpty("Last name: ")
    case 3:
      updatingStudent.birthDate = readDate("Birth date (YYYY-MM-DD): ")
    case 4:
      updatingStudent.gender = readGender("Gender (M/F/X): ")
    case 5:
      updatingStudent.previousGpa = readFloat("Previous GPA (0.0 - 4.0): ", 0.0, 4.0)
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
  studentId = readInt("Student ID: ",1, studentRepository.lastStudentID)
  if studentRepository.remove(studentId):
    print("Student removed.")
  else:
    print("[ERROR]: student not found")
  pass

def printAll():
  if not studentRepository.studentList:
    print("No students found.")
    return
  for student in studentRepository.studentList:
    print(student)

def parameterPassingDemo():
  pass





if __name__ == "__main__":
  main()