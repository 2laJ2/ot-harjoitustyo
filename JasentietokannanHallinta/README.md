# JasentietokannanHallinta

Sovelluksen avulla käyttäjän on mahdollista käyttää ja hallita urheiluseuran jäsentietokantaa. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, joilla on yksilöllisesti määritelty pääsy jäsentietoihin.

## Huomio JavaFX konfiguroinnin aiheuttamista ongelmista

JavaFx konfigurointiongelmien selvittäminen vei huomattavan paljon aikaa. Konfigurointiongelmien selvittämiseksi sovelluksen alustava versio mukailee referenssisovellusta. Tästä voidaan luopua samalla, kun alustavaa harjoitustyötä päivitetään jatkossa vastaamaan harjoitustyön omaa vaatimusmäärittelyä. Liiallinen referenssisovelluksen mukailu johtuu siten JavaFX konfigurointiongelmista ja on luonteeltaan tilapäistä.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/tyoaikakirjanpito.md)

[Testausdokumentti](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/testaus.md)

## Komentorivitoiminnot

### Ohjelman käynnistys

Ohjelman käynnistys onnistuu komentoriviltä komennolla

```
mvn compile exec:java -Dexec.mainClass=jasentietokannanhallinta.Main

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


