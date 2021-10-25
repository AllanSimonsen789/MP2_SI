# A4-MP2-API
Mini Project 2 SI

# Participants

Allan Bo Simonsen - cph-as484

Nina Lisakowski - cph-nl163

# Running the services
Download or clone the project to your computer.\
in a terminal of your choice go the root folder of the project.\
run the following command: ```docker-compose build --no-cache```\
-it may take several minutes to run depending on the power of your computer.\
Make sure you do not have any services on ports; 8761, 8091, 8092, 8093, 8094, 8095, 8096.   
When its done building run the following command: ```docker-compose up -d```\
-it may take several minutes for all the services to be fully operational.\
Succes! All the services are now running on their own port in a docker environment. \
Go to ```localhost:8761``` to check that all 6 services are running. 

# Explanation of task completion

###  1. Extend the students information system by adding new services that process: - teachers’ data -exams and exam dates
This task has been completed with the two implemented services *teacher service* & *exam service*

### 2. Enable the clients of the application to see:
- list of students who have passed their exam on System Integration, together with
their grades
- number of students who haven’t completed the Mini Project 2.
This tasks can be viewed at the following endpoints:

Returns an exam entity with a list of students and their grades for a given id \
```localhost:8094/exams/{id}/students``` \
list of legal id's: 69420, 42069, 2142, 66642, 6969

Returns an assignment entity with an integer value of the number of students and a list of the students entities that have not completed their assignement for a given id \
```localhost:8091/assignment/{id}/notdone```\
List of legal id's: 1, 2, 3, 4, 5, 6, 7

### 3. Deploy and orchestrate your microservices application in appropriate environment, such as the Netflix family of deployment services.
The project has an Eureka server implemented. This service is used in the previous task for the services to be able to call eachother.\
The Eureka service can be seen running on the following url:\
```localhost:8761```\
And the code can be found in the *EurekaServer* directory.

# Description of services

## EurekaService

### View list of services
Default page of the Eureka service. Here you can see the registered services running, there should be 6 running services if you followed the setup guide.
It can be found on ```localhost:8761```

## Teacher Service, teach-con
The teacher service provides information about the teachers, their names and email addresses. \
list of legal id: 1001, 1002, 1003, 1004 \
internal ip of this service: 8050 \
external ip of this service: 8096

#### GET - all teachers
The endpoint for this GET looks like below:
```
localhost:8096/teachers
```
This endpoint will return all the teachers available like below:
```
{
  "_embedded" : {
    "teachers" : [ {
      "tname" : "Victoria",
      "tmail" : "vi@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8096/teachers/1001"
        },
        "teacher" : {
          "href" : "http://localhost:8096/teachers/1001"
        }
      }
    }, {
      "tname" : "Benjamin",
      "tmail" : "be@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8096/teachers/1002"
        },
        "teacher" : {
          "href" : "http://localhost:8096/teachers/1002"
        }
      }
    }, {
      "tname" : "Knud",
      "tmail" : "kn@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8096/teachers/1003"
        },
        "teacher" : {
          "href" : "http://localhost:8096/teachers/1003"
        }
      }
    }, {
      "tname" : "Margrethe",
      "tmail" : "ma@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8096/teachers/1004"
        },
        "teacher" : {
          "href" : "http://localhost:8096/teachers/1004"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8096/teachers"
    },
    "profile" : {
      "href" : "http://localhost:8096/profile/teachers"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 4,
    "totalPages" : 1,
    "number" : 0
  }
}
```
#### GET - Specific teacher
The endpoint for finding a specific teacher is as below:
```
localhost:8096/teachers/{id}
```
Here you insert a valid ID, this can be either 1001 , 1002, 1003, 1004

The endpoint with an ID of 1001 will return this:
```
{
  "tname" : "Victoria",
  "tmail" : "vi@mail.dk",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8096/teachers/1001"
    },
    "teacher" : {
      "href" : "http://localhost:8096/teachers/1001"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.

#### DELETE - Specific teacher
For deleting a specific teacher it is done as below in Postman:
```
localhost:8096/teachers/{id}
```
When providing an existing ID the teacher will be deleted successfully and will return a status code 200.
If a nonexisting ID is provided a status code 500 will be thrown.

#### POST - teacher
A teacher is created in Postman through this endpoint:
```
localhost:8096/teachers/
```
An example for what could be created could be like this:
```
{
    "name": "Dora",
    "email": "dora@email.com" 
}
```
When creating a teacher then a successful return will look like below and with a status code 200:
```
{
    "name": "Dora",
    "email": "dora@email.com",
    "_links": {
        "self": {
            "href": "http://localhost:8096/teachers/1"
        },
        "teacher": {
            "href": "http://localhost:8096/teachers/1"
        }
    }
}
```

An unsuccessful scenario will appear if you write a wrong name for a wrong field, this will throw a status code 400.

#### PUT - Specific teacher
The endpoint for editing a specific teacher looks like below:
```
localhost:8096/teachers/{id}
```
When editing a specific teacher it is done in Postman with an existing ID. This could be 1001, 1002, 1003, 1004

An example for what could be edited for any teacher with a correct ID looks like below:
```
localhost:8096/teachers/1001
{
    "name": "steffen",
    "mail": "steffen@mail.com" 
}
```
If a nonexisting ID is provided a status code 404 Not Found is thrown.```

## Grade Service, grade-con
The grade service provides information about grades, id on the grade that grade is assigned to and what id of the exam.\
list of legal id: 901 - 935\
internal ip of this service: 8040\
external ip of this service: 8093

#### GET - all grades
The endpoint for this GET looks like below:
```
localhost:8093/grades
```
This endpoint will return all the grades available like below:
```
{
  "_embedded" : {
    "grades" : [ {
      "eid" : 69420,
      "sid" : "ro@mail.dk",
      "grade" : 7,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8093/grades/901"
        },
        "grade" : {
          "href" : "http://localhost:8093/grades/901"
        }
      }
    }, {
      "eid" : 69420,
      "sid" : "ba@mail.dk",
      "grade" : 4,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8093/grades/902"
        },
        "grade" : {
          "href" : "http://localhost:8093/grades/902"
        }
      }
    }, 
    
    ...]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8093/grades?page=0&size=20"
    },
    "self" : {
      "href" : "http://localhost:8093/grades"
    },
    "next" : {
      "href" : "http://localhost:8093/grades?page=1&size=20"
    },
    "last" : {
      "href" : "http://localhost:8093/grades?page=1&size=20"
    },
    "profile" : {
      "href" : "http://localhost:8093/profile/grades"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 35,
    "totalPages" : 2,
    "number" : 0
  }
}
```
#### GET - Specific grade
The endpoint for finding a specific grades is as below:
```
localhost:8093/grades/{id}
```
Here you insert a valid id of you choice.

The endpoint with an ID of 901 will return this:
```
{
  "eid" : 69420,
  "sid" : "ro@mail.dk",
  "grade" : 7,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8093/grades/901"
    },
    "grade" : {
      "href" : "http://localhost:8093/grades/901"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.

#### DELETE - Specific grade
For deleting a specific grade it is done as below in Postman:
```
localhost:8093/grades/{id}
```
When providing an existing ID the grade will be deleted successfully and will return a status code 200.
If a nonexisting ID is provided a status code 500 will be thrown.

#### POST - grade
A grade is created in Postman through this endpoint:
```
localhost:8093/grades/
```
An example for what could be created could be like this:
```
{
    "eid": "(int){id of exam}",
    "smail": "(string){mail of student}"
    "grade": "(int){grade}
}
```
When creating a grade then a successful return will look like below and with a status code 200:
```
{
  "eid" : 69420,
  "sid" : "ro@mail.dk",
  "grade" : 7,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8093/grades/901"
    },
    "grade" : {
      "href" : "http://localhost:8093/grades/901"
    }
  }
}
```

An unsuccessful scenario will appear if you write a wrong name for a wrong field, this will throw a status code 400.

#### PUT - Specific grade
The endpoint for editing a specific grades looks like below:
```
localhost:8093/grades/{id}
```
When editing a specific grade it is done in Postman with an existing ID.

An example for what could be edited for any grade with a correct ID looks like below:
```
localhost:8093/grades/69420
{
    "eid": "(int){id of exam}",
    "smail": "(string){mail of student}"
    "grade": "(int){grade}
}
```
If a nonexisting ID is provided a status code 404 Not Found is thrown.

## Assignment Service, ass-con
The Assignement service provides information about assignments, name of the assignment, the amount of study points.\
list of legal id: 1, 2, 3, 4, 5, 6, 7\
internal ip of this service: 8060\
external ip of this service: 8091

#### GET - all Assignments
The endpoint for this GET looks like below:
```
localhost:8091/assignments
```
This endpoint will return all the assignments available like below:
```
{
  "_embedded" : {
    "assignments" : [ {
      "aname" : "Microservice1",
      "astudypoints" : 15,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8091/assignments/1"
        },
        "assignment" : {
          "href" : "http://localhost:8091/assignments/1"
        }
      }
    }, ...
    
    {
      "aname" : "Microservice7",
      "astudypoints" : 10,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8091/assignments/7"
        },
        "assignment" : {
          "href" : "http://localhost:8091/assignments/7"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8091/assignments"
    },
    "profile" : {
      "href" : "http://localhost:8091/profile/assignments"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 7,
    "totalPages" : 1,
    "number" : 0
  }
}
```
#### GET - Specific assignment
The endpoint for finding a specific assignment is as below:
```
localhost:8091/assignments/{id}
```
Here you insert a valid id of you choice.

The endpoint with an ID of 1 will return this:
```
{
  "aname" : "Microservice1",
  "astudypoints" : 15,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8091/assignments/1"
    },
    "assignment" : {
      "href" : "http://localhost:8091/assignments/1"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.

#### GET - amount & list of students that havent completed specific assignemtn
The endpoint for finding a the amount of student that havent completed a specific assignment is as below:
```
localhost:8091/assignments/{id}/notdone
```
Here you insert a valid id of you choice.

The endpoint with an ID of 1 will return this:
```
{
  "assignmentID" : 1,
  "assignmentName" : "Microservice1",
  "assignmentStudypoint" : 15,
  "nrNotDoneByStudents" : 1,
  "studentList" : [ {
    "sname" : "Freya",
    "smail" : "fre@mail.us",
    "_links" : {
      "self" : {
        "href" : "http://localhost:8092/students/107"
      },
      "student" : {
        "href" : "http://localhost:8092/students/107"
      }
    }
  } ],
  "_links" : {
    "all-assignments" : {
      "href" : "http://localhost:8091/assignment/"
    },
    "self" : {
      "href" : "http://localhost:8091/assignment/1"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.


#### DELETE - Specific assignment
For deleting a specific assignment it is done as below in Postman:
```
localhost:8091/assignments/{id}
```
When providing an existing ID the assignment will be deleted successfully and will return a status code 200.
If a nonexisting ID is provided a status code 500 will be thrown.

#### POST - assignment
A assignement is created in Postman through this endpoint:
```
localhost:8091/assignments/
```
An example for what could be created could be like this:
```
{
    "aname": "(string){name of assignment}",
    "astudypoints": "(int){integer of sttudypoints}"
}
```
When creating a assignment then a successful return will look like below and with a status code 200:
```
{
  "aname" : "Microservice1",
  "astudypoints" : 15,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8091/assignments/1"
    },
    "assignment" : {
      "href" : "http://localhost:8091/assignments/1"
    }
  }
}
```

An unsuccessful scenario will appear if you write a wrong name for a wrong field, this will throw a status code 400.

#### PUT - Specific assignment
The endpoint for editing a specific assignment looks like below:
```
localhost:8091/assignment/{id}
```
When editing a specific assignment it is done in Postman with an existing ID.

An example for what could be edited for any assignment with a correct ID looks like below:
```
localhost:8091/assignment/1
{
    "aname": "(string){name of assignment}",
    "astudypoints": "(int){integer of sttudypoints}"
}
```
If a nonexisting ID is provided a status code 404 Not Found is thrown.

## Student Service, student-con
The student service provides information about the students, their names and email addresses.\
list of legal id: 101, 102, 103, 104, 105, 106, 107\
internal ip of this service: 8070\
external ip of this service: 8092

#### GET - all
The endpoint for this GET looks like below:
```
localhost:8092/students
```
This endpoint will return all the students available like below:
```
{
  "_embedded" : {
    "students" : [ {
      "name" : "Alice",
      "mail" : "al@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8092/students/101"
        },
        "student" : {
          "href" : "http://localhost:8092/students/101"
        },
        "subjects" : {
          "href" : "http://localhost:8092/students/101/subjects"
        }
      }
    }, {
      "name" : "Bob",
      "mail" : "bo@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8092/students/102"
        },
        "student" : {
          "href" : "http://localhost:8092/students/102"
        },
        "subjects" : {
          "href" : "http://localhost:8092/students/102/subjects"
        }
      }
    }, {
      "name" : "Caroline",
      "mail" : "ca@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8092/students/103"
        },
        "student" : {
          "href" : "http://localhost:8092/students/103"
        },
        "subjects" : {
          "href" : "http://localhost:8092/students/103/subjects"
        }
      }
    }, {
      "name" : "Daniel",
      "mail" : "da@mail.dk",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8092/students/104"
        },
        "student" : {
          "href" : "http://localhost:8092/students/104"
        },
        "subjects" : {
          "href" : "http://localhost:8092/students/104/subjects"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8092/students"
    },
    "profile" : {
      "href" : "http://localhost:8092/profile/students"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 4,
    "totalPages" : 1,
    "number" : 0
  }
}
```
#### GET - Specific student
The endpoint for finding a specific student is as below:
```
localhost:8092/students/{id}
```
Here you insert a valid ID, this can be either 101 , 102, 103, 104, 105, 106, 107, 108 & 109.

The endpoint with an ID of 101 will return this:
```
{
  "name" : "Alice",
  "mail" : "al@mail.dk",
  "_links" : {
    "all-students" : {
      "href" : "http://localhost:8092/students/all"
    },
    "self" : {
      "href" : "http://localhost:8092/students/101"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.

#### DELETE - Specific student
For deleting a specific student it is done as below in Postman:
```
localhost:8092/students/{id}
```
When providing an existing ID the student will be deleted successfully and will return a status code 200.
If a nonexisting ID is provided a status code 500 will be thrown.

#### POST - student
A student is created in Postman through this endpoint:
```
localhost:8092/students/
```
An example for what could be created could be like this:
```
{
    "name": "Dora",
    "email": "dora@email.com" 
}
```
When creating a student then a successful return will look like below and with a status code 200:
```
{
    "name": "Dora",
    "email": "dora@email.com",
    "_links": {
        "self": {
            "href": "http://localhost:8092/students/1"
        },
        "student": {
            "href": "http://localhost:8092/students/1"
        }
    }
}
```

An unsuccessful scenario will appear if you write a wrong name for a wrong field, this will throw a status code 400.

#### PUT - Specific student
The endpoint for editing a specific student looks like below:
```
localhost:8092/students/{id}
```
When editing a specific student it is done in Postman with an existing ID. This could be 101, 102, 103, 104, 105, 106, 107, 108, 109.

An example for what could be edited for any student with a correct ID looks like below:
```
localhost:8092/students/101
{
    "name": "steffen",
    "mail": "steffen@mail.com" 
}
```
If a nonexisting ID is provided a status code 404 Not Found is thrown.

## Report Service, report-con

The report service provides information about the report name and word count.\
There are no legal ID's since it is made in a mongodb.\
internal ip of this service: 8072\
external ip of this service: 8095

#### GET - all
The endpoint for this GET looks like below:
```
localhost:8095/report
```
This endpoint will return all the reports available like below:
```
{
  "_embedded" : {
    "reports" : [ {
      "name" : "research1",
      "wordcount" : "489",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8095/report/1"
        },
        "report" : {
          "href" : "http://localhost:8095/report/1"
        }
      }
    }, {
      "name" : "research2",
      "wordcount" : "1006",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8095/report/2"
        },
        "report" : {
          "href" : "http://localhost:8095/report/2"
        }
      }
    }, {
      "name" : "research3",
      "wordcount" : "91845",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8095/report/3"
        },
        "report" : {
          "href" : "http://localhost:8095/report/3"
        }
      }
    }, {
      "name" : "research4",
      "wordcount" : "398",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8095/report/4"
        },
        "report" : {
          "href" : "http://localhost:8095/report/4"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8095/report"
    },
    "profile" : {
      "href" : "http://localhost:8095/profile/report"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 4,
    "totalPages" : 1,
    "number" : 0
  }
}
```
#### GET - Specific report
The endpoint for finding a specific report is as below:
```
localhost:8095/report/{id}
```
Here you insert a valid ID, this could be either 1 , 2, 3, 4

The endpoint with an ID of 1 will return this:
```
{
   "name":"research1",
   "wordcount":"489",
   "_links":{
      "self":{
         "href":"http://localhost:8095/report/1"
      },
      "report":{
         "href":"http://localhost:8095/report/1"
      }
   }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.

#### DELETE - Specific report
For deleting a specific report it is done as below in Postman:
```
localhost:8095/report/{id}
```
When providing an existing ID the report will be deleted successfully and will return a status code 200.
If a nonexisting ID is provided a status code 500 will be thrown.

#### POST - report
A report is created in Postman through this endpoint:
```
localhost:8095/report/
```
An example for what could be created could be like this:
```
{
   "name":"research5",
   "wordCount":"666",
}
```
When creating a report then a successful return will look like below and with a status code 200:
```
{
   "name":"research5",
   "wordcount":"666",
   "_links":{
      "self":{
         "href":"http://localhost:8095/report/5"
      },
      "report":{
         "href":"http://localhost:8095/report/5"
      }
   }
}
```

An unsuccessful scenario will appear if you write a wrong name for a wrong field, this will throw a status code 400.

#### PUT - Specific report
The endpoint for editing a specific report looks like below:
```
localhost:8095/report/{id}
```
When editing a specific report it is done in Postman with an existing ID. This could be 1, 2, 3, 4

An example for what could be edited for any report with a correct ID looks like below:
```
localhost:8095/report/1
{
   "name":"HandIn1",
   "wordCount":"966",
}
```
If a nonexisting ID is provided a status code 404 Not Found is thrown.

## Exam Service, exam-con
The exam service provides information about exams, name of the exam, the date of the exam.\
list of legal id: 69420, 42069, 2142, 66642, 6969\
internal ip of this service: 8042\
external ip of this service: 8094

#### GET - all exams
The endpoint for this GET looks like below:
```
localhost:8094/exams
```
This endpoint will return all the exams available like below:
```
{
  "_embedded" : {
    "exams" : [ {
      "ename" : "Test",
      "edate" : "22-01-2022",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8094/exams/2142"
        },
        "exam" : {
          "href" : "http://localhost:8094/exams/2142"
        }
      }
    }, {
      "ename" : "Data Science",
      "edate" : "18-06-2022",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8094/exams/6969"
        },
        "exam" : {
          "href" : "http://localhost:8094/exams/6969"
        }
      }
    }, {
      "ename" : "System Integration",
      "edate" : "15-01-2021",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8094/exams/42069"
        },
        "exam" : {
          "href" : "http://localhost:8094/exams/42069"
        }
      }
    }, {
      "ename" : "Database Development",
      "edate" : "22-06-2022",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8094/exams/66642"
        },
        "exam" : {
          "href" : "http://localhost:8094/exams/66642"
        }
      }
    }, {
      "ename" : "Development of large Systems",
      "edate" : "21-12-2021",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8094/exams/69420"
        },
        "exam" : {
          "href" : "http://localhost:8094/exams/69420"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8094/exams"
    },
    "profile" : {
      "href" : "http://localhost:8094/profile/exams"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 5,
    "totalPages" : 1,
    "number" : 0
  }
}
```
#### GET - Specific exam
The endpoint for finding a specific exam is as below:
```
localhost:8094/exams/{id}
```
Here you insert a valid id of you choice.

The endpoint with an ID of 1 will return this:
```
{
  "ename" : "Development of large Systems",
  "edate" : "21-12-2021",
  "_links" : {
    "all-exams" : {
      "href" : "http://localhost:8094/exams/"
    },
    "self" : {
      "href" : "http://localhost:8094/exams/69420"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.

#### GET - amount & list of students that havent completed specific assignemtn
This endpoint returns a list of students and their grades for a specific exam:
```
localhost:8094/exams/{id}/students
```
Here you insert a valid id of you choice.

The endpoint with an ID of 69420 will return this:
```
{
  "examID" : 69420,
  "examName" : "Development of large Systems",
  "examDate" : "21-12-2021",
  "studentList" : [ {
    "name" : "Ronja",
    "mail" : "ro@mail.dk",
    "grade" : 7,
    "_student_links" : {
      "self" : {
        "href" : "http://c8cffacdc52a:8070/students/101"
      },
      "student" : {
        "href" : "http://c8cffacdc52a:8070/students/101"
      }
    },
    "_grade_links" : {
      "self" : {
        "href" : "http://2043c975839a:8040/grades/901"
      },
      "grade" : {
        "href" : "http://2043c975839a:8040/grades/901"
      }
    }
  }, {
    "name" : "Bastian",
    "mail" : "ba@mail.dk",
    "grade" : 4,
    "_student_links" : {
      "self" : {
        "href" : "http://c8cffacdc52a:8070/students/102"
      },
      "student" : {
        "href" : "http://c8cffacdc52a:8070/students/102"
      }
    },
    "_grade_links" : {
      "self" : {
        "href" : "http://2043c975839a:8040/grades/902"
      },
      "grade" : {
        "href" : "http://2043c975839a:8040/grades/902"
      }
    }
  }, {
    "name" : "Alice",
    "mail" : "al@mail.wld",
    "grade" : 12,
    "_student_links" : {
      "self" : {
        "href" : "http://c8cffacdc52a:8070/students/103"
      },
      "student" : {
        "href" : "http://c8cffacdc52a:8070/students/103"
      }
    },
    "_grade_links" : {
      "self" : {
        "href" : "http://2043c975839a:8040/grades/903"
      },
      "grade" : {
        "href" : "http://2043c975839a:8040/grades/903"
      }
    }
  }, {
    "name" : "Jonathan",
    "mail" : "jo@mail.us",
    "grade" : 4,
    "_student_links" : {
      "self" : {
        "href" : "http://c8cffacdc52a:8070/students/105"
      },
      "student" : {
        "href" : "http://c8cffacdc52a:8070/students/105"
      }
    },
    "_grade_links" : {
      "self" : {
        "href" : "http://2043c975839a:8040/grades/905"
      },
      "grade" : {
        "href" : "http://2043c975839a:8040/grades/905"
      }
    }
  }, {
    "name" : "Frida",
    "mail" : "fri@mail.us",
    "grade" : 7,
    "_student_links" : {
      "self" : {
        "href" : "http://c8cffacdc52a:8070/students/106"
      },
      "student" : {
        "href" : "http://c8cffacdc52a:8070/students/106"
      }
    },
    "_grade_links" : {
      "self" : {
        "href" : "http://2043c975839a:8040/grades/906"
      },
      "grade" : {
        "href" : "http://2043c975839a:8040/grades/906"
      }
    }
  }, {
    "name" : "Freya",
    "mail" : "fre@mail.us",
    "grade" : 10,
    "_student_links" : {
      "self" : {
        "href" : "http://c8cffacdc52a:8070/students/107"
      },
      "student" : {
        "href" : "http://c8cffacdc52a:8070/students/107"
      }
    },
    "_grade_links" : {
      "self" : {
        "href" : "http://2043c975839a:8040/grades/907"
      },
      "grade" : {
        "href" : "http://2043c975839a:8040/grades/907"
      }
    }
  } ],
  "_links" : {
    "all-exams" : {
      "href" : "http://localhost:8094/exams/"
    },
    "self" : {
      "href" : "http://localhost:8094/exams/69420"
    }
  }
}
```

If an nonexisting ID is provided an error code NOT FOUND with status 404 will be thrown.


#### DELETE - Specific exam
For deleting a specific exam it is done as below in Postman:
```
localhost:8094/exams/{id}
```
When providing an existing ID the exam will be deleted successfully and will return a status code 200.
If a nonexisting ID is provided a status code 500 will be thrown.

#### POST - exam
A assignement is created in Postman through this endpoint:
```
localhost:8094/exams/
```
An example for what could be created could be like this:
```
{
    "ename": "(string){name of exam}",
    "edate": "(string){date of the exam}"
}
```
When creating a exam then a successful return will look like below and with a status code 200:
```
{
  "ename" : "Development of large Systems",
  "edate" : "21-12-2021",
  "_links" : {
    "all-exams" : {
      "href" : "http://localhost:8094/exams/"
    },
    "self" : {
      "href" : "http://localhost:8094/exams/69420"
    }
  }
}
```

An unsuccessful scenario will appear if you write a wrong name for a wrong field, this will throw a status code 400.

#### PUT - Specific exam
The endpoint for editing a specific exam looks like below:
```
localhost:8094/exams/{id}
```
When editing a specific exam it is done in Postman with an existing ID.

An example for what could be edited for any exam with a correct ID looks like below:
```
localhost:8094/exam/69420
{
    "ename": "(string){name of exam}",
    "edate": "(string){date of the exam}"
}
```
If a nonexisting ID is provided a status code 404 Not Found is thrown.

