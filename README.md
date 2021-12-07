# JasentietokannanHallinta

Sovelluksen avulla käyttäjän on mahdollista käyttää ja hallita urheiluseuran jäsentietokantaa. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, joilla on yksilöllisesti määritelty pääsy jäsentietoihin.

## Huomio JavaFX konfiguroinnin aiheuttamista ongelmista

JavaFx konfigurointiongelmien selvittäminen vei huomattavan paljon aikaa. Konfigurointiongelmien selvittämiseksi sovelluksen alustava versio mukailee referenssisovellusta. Tästä voidaan luopua samalla, kun alustavaa harjoitustyötä päivitetään jatkossa vastaamaan harjoitustyön omaa vaatimusmäärittelyä. Liiallinen referenssisovelluksen mukailu johtuu siten JavaFX konfigurointiongelmista ja on luonteeltaan tilapäistä.

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

[Vaatimusmäärittely](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/tyoaikakirjanpito.md)

[Testausdokumentti](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/testaus.md)

[Arkkitehtuurikuvaus](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/arkkitehtuuri.md)

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

generoi hakemistoon _target_ suoritettavan jar-tiedoston JasentietokannanHallinta-1.0-SNAPSHOT.jar

### Ohjelman lataaminen ja käynnistäminen omalla linux-koneella

Tallenna sivulta [Release](https://github.com/2laJ2/ot-harjoitustyo/releases/tag/viikko5) löytyvä tiedosto _JasentietokannanHallinta-1.0-SNAPSHOT.jar_ omalle koneellesi esim. kansioon Downloads. 

Ohjelman voi käynnistää komentoriviltä saman tiedostokansion sisältä, mihin tiedosto _JasentietokannanHallinta-1.0-SNAPSHOT.jar_ on tallennettu (esim. Downloads). Käynnistä ohjelma komennolla 

```
java -jar JasentietokannanHallinta-1.0-SNAPSHOT.jar
```

Ohjelma luo suorituksen aikana suoritushakemistoon (esim. Downloads) tiedostot _jasentiedotList.txt_ ja _users.txt_, joihin se tallentaa sovelluksen käyttämiä tietoja. Sovellus olettaa, että sillä on nämä tiedostot käytettävissään.

### Checkstyle

Checkstyle suorittaa tiedoston checkstyle.xml määrittelemät tarkistukset komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset löytyvät avaamalla selaimella tiedosto target/site/checkstyle.html 

[checkstyle](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/checkstyle.png)
