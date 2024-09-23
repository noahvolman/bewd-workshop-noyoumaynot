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
Daarvoor nemen we eerst de bestaande *AuthenticationService* in gebruik. 
Deze wordt al geïnjecteerd in de *MovieController*.
Om de methodes te kunnen aanroepen hebben we een gebruikersnaam en wachtwoord nodig.
- Zet een username en een wachtwoord voorlopig als hardcoded String constanten in de *MovieController*.

## 3. De eerste stap naar autorisatie
In de *MovieController* bevindt zich een methode authenticate. 
Roep deze aan vanuit iedere endpoint.
Je zult zien dat je op iedere endpoint een exception krijgt.
Bouw een ControllerAdvice die zorgt voor een nette status 401 (unauthorized).
Door de isValidToken methode true laten retourneren ipv false, kun je zorgen dat de exception niet wordt gegooid.
Dit is natuurlijk niet zo netjes. Je wil hier echt controleren.

## 4. Tokens genereren
In de *AuthenticationService* staan de methodes login, isValidRequest en getUsername.
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

# WIP: Opbouw workshop

1. Introductie van repo. 
    1. Beschrijving van hoofddoel: Endpoints alleen toegankelijk maken voor bepaalde gebruikers.
        - subdoel 1 (Authenticatie): Op een betrouwbare manier erachter komen wie een gebruiker is (username/password) en een 401 geven als de gebruiker onbekend is.
        - subdoel 2 (Autorisatie): Per endpoint controleren of de gebruiker er bij mag en een 403 geven als dat niet zo is.
1. Eerst subdoel 1: Authenticatie. WIE BEN JIJ?
   1. Endpoint afschermen door te checken of een gebruiker het juiste wachtwoord weet.
        - Door een QueryParam mee te geven met het universele hardcoded wachtwoord. 401 bij fout.
        - Volgende probleem: Dit is nu wel afgeschermd, maar niet per gebruiker en een vast wachtwoord is ook niet erg veilig.
   1. UserDatabase: username/password per gebruiker bijhouden in een lijst.
        - QueryParam voor user en QueryParam voor password. User class met username/password. 
        - Voor nu hardcoded in een list, maar het is niet moeilijk voor studenten om een database tabel er bij te fantaseren.
        - Volgende probleem: Gebruikers sturen bij elk request hun username / wachtwoord op, die ook nog eens leesbaar in de adresbalk staan. Not great.
   1. Tokens: Een eigen login endpoint met username/password, daarna alle overige endpoints met token in QueryParam. Token moet worden opgeslagen in een List of Map.
        - Very nice. subdoel 1 behaald!
   1. Prettier login: 
      - Verander de login endpoint in een POST met userdata json body (username/password) en een token JSON response.
1. Subdoel 2: Autorisatie: MAG DAT?
   1. User rol: Voeg isAdmin boolean toe aan User.
      - Rol check: Maak een AuthorizationService class met een methode checkUserIsAdmin(String token). Deze moet beschikbaar zijn in je Controller. 
      - Deze AuthorizationService heeft de AuthenticationService nodig om het User object op te halen. Regel dat met Dependency Injection.
      - Probleem: Maar een enkele rol?
   1. Rollen!
      - Maak een Rollen enum met een aantal rollen: MOVIE_READER, MOVIE_DELETER, MOVIE_CREATOR, MOVIE_UPDATER, ADMIN.
      - Pas de User class aan dat deze een lijst van rollen heeft. Pas je 
      - Pas de AuthorizeService aan zodat deze een generieke methode heeft met een token en een rol als param.
      - Voeg checks toe aan de verschillende endpoints.
1. Extra / Cleanup:
   - Maak van token een class ipv String met een expiration date. Sta meerdere 'sessions' per user toe.
   - Token in cookie ipv QueryParam.
   - Voeg de rollen van een gebruiker toe aan de token (AKA claims) voor snelle verificatie in de controller.
   - Users and roles in Database.
   - User and roles management endpoints/controllers.
   - Password hashing.
   - Users in Groepen, groepen met rollen.