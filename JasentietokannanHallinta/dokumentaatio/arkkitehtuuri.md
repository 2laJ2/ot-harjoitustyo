# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on kolmitasoinen kerrosarkkitehtuuri. Koodin pakkausrakenne on seuraava:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/pakkausrakenne.jpg" width="160">

Pakkaus _jasentietokannanhallinta.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _jasentietokannanhallinta.domain_ sovelluslogiikan ja _jasentietokannanhallinta.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää viisi erillistä näkymää
- kirjautuminen
- uuden käyttäjän luominen
- jäsentietokantavalikko / jäsentietojen haku
- uuden jäsenen luominen
- jäsentietojen tarkastelu

Kukin näkymä on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olionaan. Näkymistä vain yksi näkymä kerrallaan on näkyvänä eli on sovelluksessa sijoitettuna [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on rakennettu ohjelman luokassa [jasentietokannanhallinta.ui.JasentietokannanhallintaUi](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/ui/JasentietokannanhallintaUi.java).

Käyttöliittymä ja sovelluslogiikka on pyritty pitämään täysin eristettyinä. Käyttöliittymä ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _jasentiedotServicen_ metodeja.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat [User](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/User.java) ja [Jasentiedot](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/Jasentiedot.java), jotka kuvaavat sovelluksen käyttäjiä ja käyttäjien luomia jäsentietoja.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/looginendatamalli.jpg" width="400">

Toiminnallisista kokonaisuuksista vastaa luokan _JasentiedotService_ ainoa olio. Luokassa on kaikille käyttöliittymän toiminnoille oma metodi.
Metodeja ovat esim.
- void createJasentiedot(int id, String name, String address, String phone, Boolean done, User user)
- List<Jasentiedot> getUndone()
- void markDone(int id)
- boolean login(String username)
- boolean createUser(String username, String name, String password)

_JasentiedotService_ pääsee käsiksi käyttäjiin ja jasentietoihin tietojen tallennuksesta vastaavien, pakkauksessa _jasentietojenhallinta.dao_ sijaitsevien rajapintojen _JasentiedotDao_ ja _UserDao_ toteuttavien luokkien kautta. 

JasentiedotServicen ja ohjelman muiden osien suhdetta kuvaa seuraava luokka/pakkauskaavio:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/pakkauskaavio.jpg" width="450">

## Tietojen pysyväistallennus

### Tiedostot

Sovellus tallettaa käyttäjien ja jäsentietojen tiedot erillisiin tiedostoihin.


