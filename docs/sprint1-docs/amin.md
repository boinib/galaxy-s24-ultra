# Amin's documentatie

## Ubiquitous language:
* Teacher (een teacher is een docent, deze zal exams kunnen maken in het systeem.)
* Exam (een toets bedoeld voor studenten die bestaat uit examQuestions.)
* ExamQuestion (een toetsvraag die een open- of meerkeuze-vraag kan zijn.)
* Answer (een antwoord die in een question kan worden gestopt, bevat de tekst van het antwoord en of dit een correcte antwoord is.)


## Entiteiten en value objects
* Teacher (ik heb niet teveel details gestopt in deze klasse, dit gebeurt later door iemand anders.)
* Exam (zelfde verhaal als teacher ik heb de belangrijke gegevens hierin gestopt, en een enum ExamState eraan gekoppeld die zou moeten aangeven of de exam is goedgekeurd of niet.
  de enum zelf heb ik nog geen inhoud aan gekoppeld, vandaar dat ik deze niet hierbij toevoeg.)
* ExamQuestion (de belangrijkste domeinklasse voor mijn use case (toetsvraag maken en toevoegen). In deze klasse is het mogelijk om een toetsvraag te schrijven en de antwoorden hiervoor bij toevoegen.)
  Als de vraag meer dan één antwoord bevat, wordt het beschouwd als een meerkeuze vraag.
  Er kunnen ook meerdere antwoorden correct worden gemarkeerd, in dit geval hoort het mogelijk te zijn voor de student om meerdere antwoorden te selecteren
  de examQuestion hoort ook een "tag" te bevatten, maar omdat het voor ons nog niet duidelijk was hoe we hieraan kunnen werken en of het voor nu al nodig is, is dit nog niet geïmplementeerd.
* Answer (een antwoord die in een question kan worden gestopt, bevat de tekst van het antwoord en of dit een correcte antwoord is.)
* ( Ik heb hiernaast verschillende questiontypes gemaakt, maar deze zijn nog niet geïntegreerd in de services. deze questions bestaan uit verschillende soorten vraagtypes en bevatten ook passende answer-klassen. )

locatie-path voor domein-klassen: [domain](..%2Fmonoliet%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fdomain)

## Repositories, services en controllers
* AnswerRepository

locatie-path : [AnswerRepository.java](..%2Fmonoliet%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fdata%2FAnswerRepository.java)

* ExamQuestionRepository

locatie-path : [ExamQuestionRepository.java](..%2Fmonoliet%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fdata%2FExamQuestionRepository.java)

* ExamQuestionController
  - bevat de API-requests voor het beheren van ExamQuestions.
    locatie-path : [ExamQuestionController.java](..%2Fmonoliet%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fpresentation%2FExamQuestionController.java)

* ExamQuestionService
  - bevat de logica voor het beheren van ExamQuestion.
    locatie-path : [ExamQuestionService.java](..%2Fmonoliet%2Fsrc%2Fmain%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fapplication%2FExamQuestionService.java)


## Testen
de volgende testen behoren tot de use-case, Toetsvraag maken en toevoegen;
ExamQuestiontest zowel als ExamQuestionControllerIntegrationTest.
* ExamQuestiontest
  - deze testen controleren de logica van een ExamQuestion, er word hier gekeken of het een meerkeuze vraag is wanneer er antwoorden zijn toegevoegd
    en of het mogelijk hoort te zijn om meerdere antwoorden te selecteren wanneer er meer dan één antwoord correct is.
    locatie-path :[ExamQuestionTest.java](..%2Fmonoliet%2Fsrc%2Ftest%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fdomain%2FExamQuestionTest.java)


* ExamQuestionControllerIntegrationTest
  - Deze testen controleren of de API van de ExamQuestionController goed werkt.
    het test of je een nieuwe ExamQuestion kunt toevoegen via een POST-request en of je alle opgeslagen ExamQuestions kunt ophalen met een GET-request.
    locatie-path : [ExamQuestionControllerIntegrationTest.java](..%2Fmonoliet%2Fsrc%2Ftest%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fpresentation%2FExamQuestionControllerIntegrationTest.java)


* unit-testen voor de nog-ongebruikte-question-klassen zijn hier te vinden; [examquestion](..%2Fmonoliet%2Fsrc%2Ftest%2Fjava%2Fnl%2Fhu%2Finno%2Fhulp%2Fmonoliet%2Ftestvision%2Fdomain%2Fexamquestion)

sprint 2: [amin.md](..%2Fsprint2-docs%2Famin.md)
sprint 3: [amin.md](..%2Fsprint3-docs%2Famin.md)

