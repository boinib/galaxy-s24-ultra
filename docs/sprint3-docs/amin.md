# Amin's documentatie

in iteratie 3 heb ik gewerkt in de branch "examreviewer", als de class "addTeacherConfirmation" niet bestaat in de exam-module van de main-branch, betekent dit dat dit nog niet is samengevoegd.

- docenten kunnen akkoord gaan met examens en deze word opgeslagen in de examen.
[domain](..%2F..%2Fexam%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fdomain)

- deze usecase is ook geimplementeerd in de presentatie/applicatie-laag.
[ExamService.java](..%2F..%2Fexam%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fapplication%2FExamService.java)
[ExamController.java](..%2F..%2Fexam%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fpresentation%2FExamController.java)

- ExamNotFoundException() geimplementeerd in [ExamService.java](..%2F..%2Fexam%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fapplication%2FExamService.java)

- Het maken van een exam stuurt zijn questions naar een queue. Zodra de questionmodule online is, worden de questions opgeslagen in de deze module.
[ExamService.java](..%2F..%2Fexam%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fapplication%2FExamService.java)

- postman die ik dit sprint heb gebruikt: [Galaxy s24 ultra.postman_collection (sprint3).json](Galaxy%20s24%20ultra.postman_collection%20%28sprint3%29.json)