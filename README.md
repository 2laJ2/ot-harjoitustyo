# JasentietokannanHallinta

Sovelluksen avulla käyttäjän on mahdollista käyttää ja hallita urheiluseuran jäsentietokantaa. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, joilla on yksilöllisesti määritelty pääsy jäsentietoihin.

## Huomio JavaFX konfiguroinnin aiheuttamista ongelmista

JavaFx konfigurointiongelmien selvittäminen vei huomattavan paljon aikaa. Tästä syystä sovelluksen ensimmäinen versio mukaili referenssisovellusta. Sovellusta päivitettäessä mukailua on vähenetty sitä mukaa, kuin vaatimusmäärittelyn luettelemia toiminnallisuuksia on lisätty. Sovellus on kuitenkin nykyisessä muodossaan liian suppea, jotta sitä voisi hyödyntää täysipainoisesti alkuperäiseen tarkoitukseen. Sovelluksen graafisen käyttöliittymän koodia kannattaisi selkiyttää FXML-määrittelyllä ja jäsentietojen tallennustapa kannattaisi myöhempiä jatkokehitysideoita silmälläpitäen muuttaa esim. MySQL-tietokannaksi. Tämä poistaisi monia sovelluksen käytettävyyteen vaikuttavia ongelmia, kuten esim. jäsentietojen pysyvän tallennuksen sovelluksen sulkemisen varalta. FXML-määrittelyyn ja MySQL-tietokantaan perehtyminen ja käyttönottaminen vie huomattavan paljon aikaa ja vaatisi käytännössä sovelluksen rakentamista uudelleen lähes alusta alkaen.   

## Ohjelman toimivuus

Ohjelma toteuttaa seuraavat määrittelydokumentissa kuvaillut toiminnot:

- Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai kirjautumisen onnistuessa sovelluksen käyttönäkymään

- (pää)käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä
  - salasanan täytyy olla uniikki ja pituudeltaan vähintään 10 merkkiä

- (pää)käyttäjä voi kirjautua järjestelmään
  - olemassaoleva käyttäjätunnus ja salasana syötetään kirjautumislomakkeelle (järjestelmä ei vielä toistaiseksi tarkista, onko syötetty salasana oikein)
  - jos käyttäjätunnusta ei ole olemassa, järjestelmä antaa ilmoituksen

- (pää)käyttäjä näkee seuran jäsentietokantavalikon

- (pää)käyttäjä voi valita haluamansa toiminnon
  - tietokannan muokkaus, jolloin (pää)käyttäjä siirtyy uuden jäsenen luontinäkymään (järjestelmä ei toistaiseksi tallenna jäsentietoja seuraavaa käyttökertaa varten)
  - jäsentietojen haku tietokannasta halutuilla kriteereillä, jolloin (pää)käyttäjä siirtyy jäsentietojen tarkastelu / muokkausnäkymään (toimintoa jäsentietojen muokkaus ei ole vielä lisätty)
    - järjestelmä löytää samalla käyttökerralla tallennetut jäsentiedot annetun nimen perusteella (toimintoa löydettyjen jäsentietojen tarkastelu ei ole vielä lisätty)

- (pää)käyttäjä voi siirtyä uuden jäsen luontinäkymästä ja jäsentietojen tarkastelu / muokkausnäkymästä takaisin jäsentietokantavalikkoon
  - siirryttäessä eri näkymään järjestelmä tyhjentää näkymässä olevat jäsentiedot ennen siirtymistä

- (pää)käyttäjä voi kirjautua ulos järjestelmästä
 
## Dokumentaatio

[Käyttöohje](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/2laJ2/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Ohjelman käynnistys

Ohjelman käynnistys onnistuu komentoriviltä komennolla

```
mvn compile exec:java -Dexec.mainClass=jasentietokannanhallinta.ui.JasentietokannanhallintaUi
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
```

Kattavuusraportti avataan tarkasteltavaksi avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _JasentietokannanHallinta-1.0-SNAPSHOT.jar_, jonka voi suorittaa millä tahansa koneella, jolle on asennettu Javan versio 1.8.

### Ohjelman lataaminen ja käynnistäminen omalla linux-koneella

Tallenna sivulta [Release](https://github.com/2laJ2/ot-harjoitustyo/releases/tag/viikko5) löytyvä tiedosto _JasentietokannanHallinta-1.0-SNAPSHOT.jar_ omalle koneellesi esim. kansioon Downloads. 

Ohjelman voi käynnistää komentoriviltä saman tiedostokansion sisältä, mihin tiedosto _JasentietokannanHallinta-1.0-SNAPSHOT.jar_ on tallennettu (esim. Downloads). Käynnistä ohjelma komennolla 

```
java -jar JasentietokannanHallinta-1.0-SNAPSHOT.jar
```

Ohjelma luo suorituksen aikana suoritushakemistoon (esim. Downloads) tiedostot _jasentiedotList.txt_ ja _users.txt_, joihin se tallentaa sovelluksen käyttämiä tietoja. Sovellus olettaa, että sillä on nämä tiedostot käytettävissään.

### JavaDoc

JavaDoc luodaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia tarkastellaan avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Checkstyle suorittaa tiedoston checkstyle.xml määrittelemät tarkistukset komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset löytyvät avaamalla selaimella tiedosto target/site/checkstyle.html 

[checkstyle](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/checkstyle.png)
