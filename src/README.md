# Introductie
Deze workshop is onderdeel van de BEWD course aan de Hogeschool Arnhem Nijmegen.
In deze workshop leer je stap voor stap hoe je authenticatie en autorisatie toepast in een REST applicatie.

# Workshop
## 1. Clone deze repo
Gebruik je favoriete git client, download de zip van github of clone direct vanuit intellij (File -> new project from version control).
Bekijk de code. Er zijn al een aantal endpoints aanwezig. 
In de loop van deze workshop gaan we onderscheid maken in wie deze endpoints mogen benaderen.

## 2. Gebruiken TokenService
Nu kan nog iedereen elke endpoint benaderen. Dat willen we niet. We willen naar een systeem waar je moet inloggen om de juiste toegang te krijgen.
Daarvoor nemen we eerst de bestaande tokenservice in gebruik. 
Zorg dat deze geïnjecteerd wordt in de moviecontroller.
Om de methodes te kunnen aanroepen hebben we een gebruikersnaam en wachtwoord nodig.
Zet deze voorlopig als constanten in de moviecontroller.

## 3. De eerste stap naar autorisatie
In de moviecontroller bevindt zich een methode authenticate. 
Roep deze aan vanuit iedere endpoint. 
Je zult zien dat je nu geen endpoints meer kan benaderen. 
Om dit weer mogelijk te maken, kun je de isValidToken methode true laten retourneren ipv false.
Dit is natuurlijk niet zo netjes. Je wil hier echt controleren.

## 4. Tokens genereren
In de authenticationcontroller staan de methodes login, isValidRequest en getUsername.
Herschrijf deze methodes zodanig dat er een usertoken wordt gegeneerd en de juiste gegevens worden teruggegeven.

## 5 Login endpoint maken
Maak een methode login die je bindt aan de url /login. 
Voor nu maken we er get request van zonder parameters.
In de login methode roep je met je (constante) username en wachtwoord als parameters, de methode autenhticationService.login aan.
Bewaar het gegenereerde token in een publieke variabele. 
Gebruik deze waarde in de authentication methode.
Nu werken de endpoints alleen als je eerst /login hebt uitgevoerd

## 6 Username en wachtwoord controleren
De volgende stap is het uitbreiden van de /login url met username en wachtwoord.
Voor nu geven we deze als parameters mee in de url.
Verwijder de username en wachtwoord constanten uit de controller. Deze hebben we niet meer nodig. We krijgen het immers als parameter mee.

## 7. Token meegeven in berichten
Vanaf nu gaan we de token steeds mee geven ieder bericht.

# TODO: 
- Eigenlijk moeten we ergens op username wachtwoord controleren
- Authenticatie. Twee gebruikers. De ene mag alles bekijken. De andere mag ook deleten en inserten.