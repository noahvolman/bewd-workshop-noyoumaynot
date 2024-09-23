# Introductie
Deze workshop is onderdeel van de BEWD course aan de Hogeschool Arnhem Nijmegen.
In deze workshop bouwen we *zelf* eenvoudige authenticatie en autorisatie om te leren wat het is en hoe dit zou kunnen werken.

### Security in Spring Boot?
Het is ongebruikelijk om dit zelf te bouwen.
Spring Boot heeft immers ook hiervoor handige functionaliteit. Deze heeft echter een hoge leercurve.
De kracht zit hem in de magie, maar als het niet werkt heb je veel kennis nodig om dit te herstellen.

### Zelf aan de slag
Het doel van de workshop is om een beter inzicht te krijgen hoe authenticatie en autorisatie in elkaar steekt.
Dat bereiken we door stapsgewijs door de materie te gaan.

# Workshop
## 1. Clone deze repo
Gebruik je favoriete git client, download de zip van github of clone direct vanuit intellij (File -> new project from version control).
Bekijk de code en run het. Er zijn al een aantal endpoints aanwezig. Test deze met Postman.
In de loop van deze workshop gaan we onderscheid maken in wie deze endpoints mogen benaderen.

## 2. Gebruiken TokenService
Nu kan nog iedereen elke endpoint benaderen. Dat willen we niet. We willen naar een systeem waar je moet inloggen om de juiste toegang te krijgen.
Daarvoor nemen we eerst de bestaande authenticationservice in gebruik. 
Deze wordt al geïnjecteerd in de moviecontroller.
Om de methodes te kunnen aanroepen hebben we een gebruikersnaam en wachtwoord nodig.
Zet deze voorlopig als constanten in de moviecontroller.

## 3. De eerste stap naar autorisatie
In de moviecontroller bevindt zich een methode authenticate. 
Roep deze aan vanuit iedere endpoint. 
Je zult zien dat je op iedere endpoint een exception krijgt.
Bouw een ControllerAdvice die zorgt voor een nette status 401 (unauthorized).
Door de isValidToken methode true laten retourneren ipv false, kun je zorgen dat de exception niet wordt gegooid.
Dit is natuurlijk niet zo netjes. Je wil hier echt controleren.

## 4. Tokens genereren
In de authenticationcontroller staan de methodes login, isValidRequest en getUsername.
Herschrijf deze methodes zodanig dat er een usertoken wordt gegeneerd en de juiste gegevens worden teruggegeven.

## 5 Login endpoint maken
Maak een methode login die je bindt aan de url /login. 
Voor nu maken we er get request van zonder parameters.
In de login methode roep je met je (constante) username en wachtwoord als parameters, de methode authenticationService.login aan.
Bewaar het gegenereerde token in een publieke variabele. 
Gebruik deze waarde in de authentication methode.
Nu werken de endpoints alleen als je eerst /login hebt uitgevoerd

## 6 Username en wachtwoord controleren
De volgende stap is het uitbreiden van de /login url met username en wachtwoord.
Voor nu geven we deze als parameters mee in de url.
Verwijder de username en wachtwoord constanten uit de controller. Deze hebben we niet meer nodig. We krijgen het immers als parameter mee.
Controleer *hardcoded* op username wachtwoord in de AuthenticationService. Maak alleen een token als het wachtwoord klopt.
(Met hardcoded wordt bedoeld dat je de gegevens niet hoeft te checken tegen een database, maar dat de juiste waarden 'hard' in de if staan).

## 7. Token meegeven in berichten
Vanaf nu gaan we de token steeds mee geven met ieder bericht.
Verander de authentication methode zodanig dat deze de token uit het bericht haalt.

## 8. Een tweede stap naar autorisatie
Het is natuurlijk suf om de username en wachtwoord mee te geven als parameters.
Maak daarom nu een user klasse. Deze heeft een username en een wachtwoord.
Geef in plaats van de url parameters een json object mee met daarin username en wachtwoord. Maak van de get een post.

Maak ook een klasse rol. Attributen zijn naam (String) en beheerder (boolean, true indien beheerder.).
Definieer 2 rollen in de constructor van AuthenticationService. Een beheerder en een eindgebruiker.
Maak voor beide rollen een gebruiker met wachtwoord.

## 9. Autorisatie
Bouw nu dat enkel de user met de beheerderrol, films mag toevoegen en verwijderen.

## Extra:
Maak de Tabellen in H2 en haal daar de data vandaan (user en movie)

# TODO
- Wachtwoord hashing naar de H2 database
