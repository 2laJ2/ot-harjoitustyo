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
  - olemassaoleva käyttäjätunnus ja salasana syötetään kirjautumislomakkeelle
  - jos käyttäjätunnusta ei ole olemassa, järjestelmä antaa ilmoituksen
  - kirjautuminen onnistuu, jos syötetty salasana on oikea
  - jos syötetty salasana on väärä, järjestelmä antaa ilmoituksen

- (pää)käyttäjä näkee seuran jäsentietokantavalikon / varsinaisen päänäkymän
  - (pää)käyttäjä voi valita haluamansa toiminnon
  - jäsentietojen haku tietokannasta halutuilla kriteereillä, jolloin järjestelmä näyttää jäsenen nimi-, osoite- ja puhelinnumerotiedot 
    - järjestelmä löytää samalla käyttökerralla järjestelmään tallennetun jäsenen annetun nimen perusteella (toistaiseksi järjestelmä ei vielä tallenna jäseniä seuraavaa sovelluksen käynnistyskertaa varten)    
  - uuden jäsenen luominen 
    - jäsenestä tallennetaan jäsentietoihin nimi, osoite ja puhelinnumero
  - jäsentietojen muokkaus
    - aiemmin luodun jäsenen osoite- ja puhelinnumerotiedon voi vaihtaa, jolloin ohjelma ei tarkasta, täyttävätkö osoite- ja puhelinnumerotiedot vähimmäispituusvaatimuksen
      (tämä on hyödyllinen ominaisuus siltä varalta, että jäsenen todelliset osoite- ja puhelinnumerotiedot eivät täytä annettuja kriteerejä)
    - nimeä ei voi vaihtaa muokkaustoiminnolla, jotta vältytään tilanteelta, missä on tehty kirjoitusvirhe ja tallennettu virheelliset tiedot
  - jäsenen poistaminen
    - toiminto poistaa pysyvästi jäsenen, jonka nimi on jäsentietokentässä (ei jäsentietojen haku -kentässä)
      (tämä on tarpeellinen toiminto tilanteessa, missä halutaan vaihtaa jäsenen nimi - ensin luodaan uudella nimellä kokonaan uusi jäsen, minkä jälkeen poistetaan vanhalla nimellä luotu jäsen)

- siirryttäessä näkymästä toiseen järjestelmä tyhjentää tekstikentät, jolloin siirryttäessä samaan näkymään uudelleen näkymässä ei ole edellisen käyttökerran tietoja / ilmoituksia

- myöhemmin lisättävillä normaaleilla käyttäjillä voidaan rajoittaa pääsy vain osaan jäsentietokannan tiedoista 

- käyttäjä voi kirjautua ulos järjestelmästä
- seuraavalla käyttökerralla järjestelmä muistaa käyttäjän, käyttäjä voi kirjautua järjestelmään antamalla käyttäjätunnuksen ja salasanan, jonka oikeellisuuden järjestelmä tarkistaa

## Dokumentaatio

[Käyttöohje](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Viikko 6](https://github.com/2laJ2/ot-harjoitustyo/releases/tag/viikko6)

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

Tallenna sivulta [Release](https://github.com/2laJ2/ot-harjoitustyo/releases/tag/viikko6) löytyvä tiedosto _JasentietokannanHallinta-1.0-SNAPSHOT.jar_ omalle koneellesi esim. kansioon Downloads. 

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
