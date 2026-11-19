from datetime import datetime

# "Value cannot be empty." is only reached if the user enters an empty string or whitespace
def readNonEmpty(prompt):
    while True:
        value = input(prompt).strip()
        if value:
            return value
        print("[ERROR]: Value cannot be empty.")

# date must be in the format YYYY-MM-DD and cannot be in the future. If the user enters the wrong format, an error message is displayed and the user is prompted to enter the date again. If the user enters a date in the future, an error message is displayed and the user is prompted to enter the date again.
def readDate(prompt):
    while True:
        dateStr= input(prompt).strip()
        if dateStr is None or dateStr == "":
            print("[ERROR]: Value cannot be empty.")
            continue
        try:
            
                date = datetime.strptime(dateStr, "%Y-%m-%d")
                if date > datetime.now():
                    print("[ERROR]: Date of birth cannot be in the future.")
                    continue
                return date
        except ValueError:
            print("[ERROR]: Invalid date, use YYYY-MM-DD.")

#asks until the user enters a valid gender (M, F, or X). If the user enters an invalid gender, an error message is displayed and the user is prompted to enter the gender again. Lowercase is accepted
def readGender(prompt):
    while True:
        gender = input(prompt).strip().upper()
        if gender is None or gender == "":
            print("[ERROR]: Value cannot be empty.")
            continue
        if gender in ["M", "F", "X"]:
            return gender[0]
        else:
          print("[ERROR]: Gender must be M, F, or X.")
          continue;

#Python does not have a double data type, so float is used instead.
def readFloat(prompt, min, max):
    while True:
      value = float(input(prompt))
      if value is None or value == "":
        print("[ERROR]: Value cannot be empty.")
        continue
      try:
        if min <= value <= max:
          return value
        print(f"[ERROR]: Please enter a whole number between {min} and {max}.")
      except ValueError:
        print(f"[ERROR]: Please enter a whole number between {min} and {max}.")

#asks until the user enters a valid number between min and max. Min and max are included
def readInt(prompt, min, max):
    while True:
        value = int(input(prompt))
        if value is None or value == "":
            print("[ERROR]: Value cannot be empty.")
            continue
        try:
            if min <= value <= max:
                return value
            else:
                print(f"[ERROR]: Please enter a whole number between {min} and {max}.")
        except ValueError:
            print(f"[ERROR]: Please enter a whole number between {min} and {max}.")

