#Projet STAR DOC

Ces informations sont très importantes en cas de modifications du projet

-----------------

##Bien se repérer 

Vous trouverez dans le dossier 

    /var/www/frontOfficeStar
    
Le contenu du frontOffice qui est un projet GITHUB dont le repository pointe sur cette URL :

https://github.com/zmiklos/frontOfficeStar

(pour plus d'informations à propos du front-office, veuillez consulter le README qui lui est associé).

### Où est stocké l'ensemble du projet?

    /home/projet/executable_star
    
Vous y retrouverez plusieurs dossiers : 

* `GTFS_Files_Folder` : contient les dernières versions téléchargées du GTFS (une fois le fichier téléchargé, il met à jour la collection MongoDB et il n'y aura absolument aucun impact si celui-ci est supprimé).
* `mongo_save` : la ou est stockée la sauvegarde journalière de la base de données MongoDB.
* `gtfs.jar` : jar servant à effectuer la mise à jour des données données brutes (GTFS) de la star.
* `log_CRON_call_api.log` : fichier de log utile pour le debug du projet pour l'exécution du jar permettant la récolte de données. 
* `map_reduce.js` : fichier Javascript contenant les tables à mettre à jour pour les données agrégées.
* `script_V4.sh` : fichier qui est exécuté via la tache cron pour la récolte de données, il va désigner quelle version on désire charger automatiquement parmi les jar suivants que nous avons réalisé.
    *  `call_api_v1.sh` collecte un arret (juste un)
    *  `call_api_v2.sh` collecte tous les arrets d' une ligne donnée
    *  `call_api_v3.sh` collecte toutes les lignes du réseau STAR
    *  `call_api_v4.sh` inclut des données supplémentaires (météo , travaux)

 Seule la version 3 et 4 sont compatibles avec le front-office.

### Je désire modifier la fréquence des appels aux sauvegardes BDD/au programme de récolte de données  (cron)

La commande ci-dessous vous liste les différentes actions que nous avons automatisé via les tâches cron.

    $ crontab -e

 Voici les différentes tâches cron que nous utilisons et leurs significations :

* "Exécute le fichier `scriptV4.sh` et n'écrit pas le résultat de la commande"  (effectue la collecte de données).


     */6  *  *  *  * sh /home/projet/executable_star/scriptV4.sh > /dev/null
    
* "Exécute le fichier `mongo_save.sh`"  (effectue la sauvegarde de la base de données MongoDB tous les jours à 2h du matin).  


    0  2 *  *  *  sh /home/projet/executable_star/mongo_save/mongo_save.sh

*  "Exécute le fichier `map_reduce.js` via les commandes mongoDB"  (effectue la mise à jour des données agrégées tous les jours à 1h du matin).


    0  1 *  *  *  mongo star < /home/projet/executable_star/map_reduce.js

*  "Exécute le jar `gtfs.jar`"  (effectue la mise à jour des données données brutes (GTFS) de la star tous les jours à 4h du matin).


    *  0  4 *  *  *  java -jar /home/projet/executable_star/gtfs.jar
    
    
Pour plus d'informations à propos des tâches cron :
http://doc.ubuntu-fr.org/cron

(les tâches cron de Debian et Ubuntu sont configurées de la même façon).

-----------------

##L'installation/configuration du serveur


### Installer MongoDB
suivre tutoriel suivant :
https://docs.mongodb.com/v3.0/tutorial/install-mongodb-on-ubuntu/

###Installer Java 8

####Installation

As a matter of best practice we’ll update our packages:
```sh
$ sudo apt-add-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```

####Vérification

Le lancement de la commande devrait donner un résultat similaire à celui-ci
```sh
$ java -version
java version "1.8.0_91"
Java(TM) SE Runtime Environment (build 1.8.0_91-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.91-b14, mixed mode)

```

-----------------

##Autre

###Générer le jar sur Eclipse

"File"-> "Export"
Entrer sélectionner Java/Runnable JAR file

Il doit y avoir deux JAR à générer :

- <b>gtfs.jar</b> qui doit pointer sur le main : <b>MainGTFS.java</b>
- <b>call_api_v[n].jar</b> qui doit pointer sur le main : <b>App.java</b>

###Générer un export des données CSV avec MongoDB

se positionner dans le répertoire 
```sh
$ cd /home/projet/executable_star
```

Générer un export de la table arret :
```sh
$ mongoexport --db star --collection arret --fields _id,arret_id,code,name,desc,lat,lon,zone_id,url,location_type,parent_station,timezone,wheelchair_boarding --type=csv --out ./arret.csv
```

Générer un export de la table ligne :
```sh
$ mongoexport --db star --collection ligne --fields _id,id_ligne,id_agency,short_name,long_name,desc,type,url,color,text_color --type=csv --out ./ligne.csv
```

Générer un export de la table arretbus :
```sh
$ mongoexport --db star --collection arretbus --fields _id,date_requete,id_arret,id_ligne,direction,en_tete,num_vehicule,precision,prevu,reel,diff_TR,temperature,humidite,meteo --type=csv --out ./arretbus.csv
```
