## Feedback
- [x] waarom field injection in controllers? Liever constructor injection
- [ ] answers objecten hebben geen betekenis. Waar zie ik bijv dat een geordend antwoord wordt gemaakt?
- [x] Maak een cesuur object 
- [ ] Liever interfaces van klas question zoals bij antwoord
- [ ] checkForCorrectAnswersAmount --> naamgeving + lijkt rommelig?
- [ ] checkForCorrectAnswersAmount --> aantal zou configueerbaar moeten zijn
- [x] JsonFormat hoort niet in het domein
- [ ] Voorgegenereerde getters en setters, doe dat nou niet
- [x] Een geparametiseerde test voor daterange zou mooi zijn
- [ ] Meerdere answer objecten?
- [x] Wat is het verschil tussen intake en examen registratie?
- [ ] De klassen zijn verder leeg dus missen betekenis
- [x] Wat moet classroom zijn in de code?
- [ ] Wat moet de methode intakeExam in examintakeservice voorstellen? deze methode heeft teveel verantwoordelijkheden

### RPC
- [ ] nog niet alle vorige feedback is afgerond
- [x] resttemplate is geen servicelaag verantwoordelijkheid (presentation of api-laag)
- [ ] Geen exception handling in controllers, en zeer minimaal in domein
- [x] -Dmaven.test.skip=true --> niet doen, dit wil je juist in je CI
- [x] Deployment verder netjes opgelost met de shade plugin
- [ ] Het lijkt erop dat er twee connecties zijn?
- [x] De compiler plugin hoeft niet ook nog in de pom van exam
