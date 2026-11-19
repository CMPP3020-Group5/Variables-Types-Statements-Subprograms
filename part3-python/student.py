

from decimal import ROUND_HALF_UP, Decimal


class Student:
  def __init__(self, firstName, lastName, birthDate, gender, previousGpa, currentSemester, program, numberOfCourses):
    self.firstName = firstName
    self.lastName = lastName
    self.birthDate = birthDate
    self.gender = gender
    self.previousGpa = previousGpa
    self.currentSemester = currentSemester
    self.program = program
    self.numberOfCourses = numberOfCourses

  def __str__(self):
        return (
            f"ID: {self.studentID} | Name: {self.firstName} {self.lastName}"
            f" | DOB: {self.birthDate} | Gender: {self.gender}"
            f" | GPA: {formatGpa(self.previousGpa)} | Semester: {self.currentSemester}"
            f" | Program: {self.program} | Courses: {self.numberOfCourses}"
        )

def formatGpa(gpa: float) -> str:
    # Use decimal half-up rounding to match Java's %.2f formatting.
    return str(Decimal(str(gpa)).quantize(Decimal("0.01"), rounding=ROUND_HALF_UP))

class StudentRepository:
  def __init__(self):
    self.studentList: list[Student] = []
    self.lastStudentID = 0

  #student ID is assigned and incremented when a student is added to the list
  def insert(self, student: Student):
    self.lastStudentID += 1
    student.studentID = self.lastStudentID
    self.studentList.append(student)

  def findStudentById(self, studentId: int):
    for student in self.studentList:
      if student.studentID == studentId:
        return student
    return None

  def modify(self,studentId: int, updatedStudent: Student):
    for student in self.studentList:
      if student.studentID == studentId:
        student.firstName = updatedStudent.firstName
        student.lastName = updatedStudent.lastName
        student.birthDate = updatedStudent.birthDate
        student.gender = updatedStudent.gender
        student.previousGpa = updatedStudent.previousGpa
        student.currentSemester = updatedStudent.currentSemester
        student.program = updatedStudent.program
        student.numberOfCourses = updatedStudent.numberOfCourses
        return True

  def remove(self, studentId: int):
    for student in self.studentList:
      if student.studentID == studentId:
        self.studentList.remove(student)
        return True
    return False

  

  
