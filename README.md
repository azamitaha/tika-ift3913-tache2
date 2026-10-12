Welcome to Apache Tika  <https://tika.apache.org/>
=================================================

[![license](https://img.shields.io/github/license/apache/tika.svg?maxAge=2592000)](http://www.apache.org/licenses/LICENSE-2.0)
[![Jenkins](https://img.shields.io/jenkins/s/https/ci-builds.apache.org/job/Tika/job/tika-main-jdk17.svg?maxAge=3600)](https://ci-builds.apache.org/job/Tika/job/tika-main-jdk17/)
[![Jenkins tests](https://img.shields.io/jenkins/t/https/ci-builds.apache.org/job/Tika/job/tika-main-jdk17.svg?maxAge=3600)](https://ci-builds.apache.org/job/Tika/job/tika-main-jdk17/lastBuild/testReport/)
[![Maven Central](https://img.shields.io/maven-central/v/org.apache.tika/tika.svg?maxAge=86400)](http://search.maven.org/#search|ga|1|g%3A%22org.apache.tika%22)

Apache Tika(TM) is a toolkit for detecting and extracting metadata and structured text content from various documents using existing parser libraries.

Tika is a project of the [Apache Software Foundation](https://www.apache.org).

Apache Tika, Tika, Apache, the Apache feather logo, and the Apache Tika project logo are trademarks of The Apache Software Foundation.

Quick Start
===========

**Parse a file in Java:**

```java
import org.apache.tika.Tika;

Tika tika = new Tika();
String text = tika.parseToString(new File("document.pdf"));
System.out.println(text);
```

**From the command line:**

```bash
java -jar tika-app-*.jar --text document.pdf
```

**Maven dependency:**

```xml
<dependency>
    <groupId>org.apache.tika</groupId>
    <artifactId>tika-parsers-standard-package</artifactId>
    <version>4.x.y</version>
    <type>pom</type>
</dependency>
```

Getting Started
===============
Pre-built binaries of Apache Tika standalone applications are available
from https://tika.apache.org/download.html . Pre-built binaries of all the
Tika jars can be fetched from Maven Central or your favourite Maven mirror.

**Tika 2.X and support for Java 8 reached End of Life (EOL) in April, 2025. 
See [Tika Roadmap 2.x, 3.x and beyond](https://cwiki.apache.org/confluence/display/TIKA/Tika+Roadmap+--+2.x%2C+3.x+and+Beyond).** 

Tika is based on **Java 17** and uses the [Maven 3](https://maven.apache.org) build system.
**N.B.** [Docker](https://www.docker.com/products/personal) is used for tests in tika-integration-tests. If Docker is not installed, those tests are skipped.

To build Tika from source, use the following command in the main directory:

    ./mvnw clean install

The Maven wrapper (`mvnw`) is included in the repository and will automatically download
the correct Maven version if needed. On Windows, use `mvnw.cmd` instead.

The build consists of a number of components, including a standalone runnable jar that you can use to try out Tika features. You can run it like this:

    java -jar tika-app/target/tika-app-*.jar --help


To build a specific project (for example, tika-server-standard):

    ./mvnw clean install -am -pl :tika-server-standard

If the ossindex-maven-plugin is causing the build to fail because a dependency
has now been discovered to have a vulnerability:

    ./mvnw clean install -Dossindex.skip


Faster Builds
=============

**Fast profile** - Use `-Pfast` to skip tests, checkstyle, and spotless:

    ./mvnw clean install -Pfast

**Parallel builds** - Add `-T1C` to build with 1 thread per CPU core:

    ./mvnw clean install -Pfast -T1C

**Maven Daemon (mvnd)** - Keeps a warm JVM running for 2-3x faster rebuilds:

```bash
# Install: https://github.com/apache/maven-mvnd
# macOS: brew install mvndaemon/tap/mvnd

# Use exactly like mvn
mvnd clean install -Pfast
mvnd test -pl :tika-core
```

**Combine both** for maximum speed during development:

    mvnd clean install -Pfast -T1C


Reproducible Builds
===================

Apache Tika supports [reproducible builds](https://reproducible-builds.org/). This means
that building the same source code with the same JDK version should produce
byte-for-byte identical artifacts, regardless of the build machine or time.

Key configuration:
- `project.build.outputTimestamp` is set in `tika-parent/pom.xml`
- All Maven plugins are configured to produce deterministic output

To verify the build plan supports reproducibility:

    ./mvnw artifact:check-buildplan

To verify two builds produce identical artifacts:

    ./mvnw clean install -DskipTests
    mv ~/.m2/repository/org/apache/tika tika-build-1
    ./mvnw clean install -DskipTests
    diff -r tika-build-1 ~/.m2/repository/org/apache/tika


Maven Dependencies
==================

Apache Tika provides *Bill of Material* (BOM) artifact to align Tika module versions and simplify version management. 
To avoid convergence errors in your own project, import this
bom or Tika's parent pom.xml in your dependency management section.

If you use Apache Maven:

```xml
<project>
  <dependencyManagement>
    <dependencies>
      <dependency>
       <groupId>org.apache.tika</groupId>
       <artifactId>tika-bom</artifactId>
       <version>4.x.y</version>
       <type>pom</type>
       <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <dependency>
      <groupId>org.apache.tika</groupId>
      <artifactId>tika-parsers-standard-package</artifactId>
      <type>pom</type>
      <!-- version not required since BOM included -->
    </dependency>
  </dependencies>
</project>
```

For Gradle:

```kotlin
dependencies {
  implementation(platform("org.apache.tika:tika-bom:4.x.y"))

  // version not required since bom (platform in Gradle terms)
  implementation("org.apache.tika:tika-parsers-standard-package@pom")
}
```

Migrating to 4.x
================
TBD

Contributing
============
See [CONTRIBUTING.md](CONTRIBUTING.md) and https://tika.apache.org/contribute.html

[![contributors](https://contributors-img.web.app/image?repo=apache/tika)](https://github.com/apache/tika/graphs/contributors)

Building from a Specific Tag
============================
Let's assume that you want to build the 3.0.1 tag:
```
0. Download and install hub.github.com
1. git clone https://github.com/apache/tika.git
2. cd tika
3. git checkout 3.0.1
4. ./mvnw clean install
```

If a new vulnerability has been discovered between the date of the
tag and the date you are building the tag, you may need to build with:

```
4. ./mvnw clean install -Dossindex.skip
```

If a local test is not working in your environment, please notify
 the project at dev@tika.apache.org. As an immediate workaround,
 you can turn off individual tests with e.g.:

```
4. ./mvnw clean install -Dossindex.skip -Dtest=\!UnpackerResourceTest#testPDFImages
```

License (see also LICENSE.txt)
==============================

Collective work: Copyright 2011 The Apache Software Foundation.

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.  See the NOTICE file distributed with this work for additional information regarding copyright ownership.  The ASF licenses this file to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at

<https://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.

Apache Tika includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in the LICENSE.txt file.

Export Control
==============

This distribution includes cryptographic software.  The country in which you currently reside may have restrictions on the import, possession, use, and/or re-export to another country, of encryption software.  BEFORE using any encryption software, please  check your country's laws, regulations and policies concerning the import, possession, or use, and re-export of encryption software, to  see if this is permitted.  See <http://www.wassenaar.org/> for more information.

The U.S. Government Department of Commerce, Bureau of Industry and Security (BIS), has classified this software as Export Commodity Control Number (ECCN) 5D002.C.1, which includes information security software using or performing cryptographic functions with asymmetric algorithms.  The form and manner of this Apache Software Foundation distribution makes it eligible for export under the License Exception ENC Technology Software Unrestricted (TSU) exception (see the BIS Export Administration Regulations, Section 740.13) for both object code and source code.

The following provides more details on the included cryptographic software:

Apache Tika uses the Bouncy Castle generic encryption libraries for extracting text content and metadata from encrypted PDF files.  See <http://www.bouncycastle.org/> for more details on Bouncy Castle.  

Mailing Lists
=============

* user@tika.apache.org - About using Tika
* dev@tika.apache.org - About developing Tika

Subscribe by sending a message to `{list}-subscribe@tika.apache.org`.

Issue Tracker
=============

https://issues.apache.org/jira/browse/TIKA

Security
========

See [SECURITY.md](SECURITY.md) and https://tika.apache.org/security.html
---

# IFT3913 — Tâche 2 : Génération de tests avec l'intelligence artificielle

**Équipe :** Taha AZAMI et Nourdin AZAMI  
**Université :** Université de Montréal  
**Projet étudié :** Apache Tika — module `tika-core`  
**Classe étudiée :** `org.apache.tika.utils.XMLReaderUtils`

Ce rapport présente notre analyse des tests existants, l'utilisation
de ChatUniTest avec un modèle de langage local exécuté par Ollama,
la correction des tests générés et les résultats de l'analyse de
mutation réalisée avec PITest.

## 1. Choix de la classe et analyse initiale

Nous avons choisi le projet Apache Tika, plus précisément le module
`tika-core` et la classe `org.apache.tika.utils.XMLReaderUtils`.

Cette classe fournit des méthodes utilitaires pour manipuler et
sécuriser les lecteurs et parseurs XML. Nous l'avons sélectionnée
parce qu'elle possède déjà des tests JUnit, mais que sa couverture
est inférieure à 100 %. Elle constitue donc un bon cas d'étude pour
évaluer l'apport de tests générés par intelligence artificielle.

### 1.1 Tests existants

Les tests existants se trouvent dans le fichier :

`tika-core/src/test/java/org/apache/tika/utils/XMLReaderUtilsTest.java`

Avant nos modifications, cette classe de tests contenait 6 tests JUnit.

Nous avons vérifié le fonctionnement du module avec Maven.
L'exécution initiale des tests de `tika-core` s'est terminée
avec succès : 746 tests réussis et 3 tests ignorés.

### 1.2 Analyse de mutation initiale

Nous avons utilisé PITest 1.19.1 avec son intégration JUnit 5
pour analyser la classe `XMLReaderUtils`.

Résultats initiaux :

| Indicateur | Résultat |
|---|---:|
| Mutants générés | 181 |
| Mutants tués | 21 |
| Score de mutation | 12 % |
| Mutants sans couverture | 77 |
| Couverture des lignes | 48 % |
| Force des tests | 20 % |

Ces résultats montrent que les tests initiaux ne détectent
qu'une faible proportion des modifications artificielles
introduites par PITest.

Le rapport HTML initial est conservé dans le dossier
[`pitest-initial/`](pitest-initial/index.html).

## 2. Génération de tests avec ChatUniTest et Ollama

### 2.1 Configuration

Nous avons intégré le plugin Maven ChatUniTest 2.1.1 au module
`tika-core` afin de générer des tests JUnit avec un modèle de
langage exécuté localement.

Notre environnement comprend :
- Java 17 et Maven Wrapper 3.9.12 ;
- Ollama 0.40.3 ;
- le modèle ouvert `qwen2.5-coder:3b` ;
- ChatUniTest 2.1.1.

ChatUniTest ne reconnaissant pas directement le nom
`qwen2.5-coder:3b`, nous avons créé l'alias local
`codeqwen:v1.5-chat` avec la commande :

    ollama cp qwen2.5-coder:3b codeqwen:v1.5-chat

Cet alias désigne le même modèle `qwen2.5-coder:3b`, et non
un modèle CodeQwen téléchargé séparément.

Le plugin communique avec Ollama à l'adresse locale
`http://localhost:11434/v1/chat/completions`.

### 2.2 Première génération

Nous avons demandé à ChatUniTest de générer un test pour
la méthode `XMLReaderUtils.getAttrValue()`.

La génération a duré environ 10 minutes. Le plugin a proposé
11 méthodes de test, mais le fichier produit n'a pas compilé
sans intervention humaine.

Nous avons identifié plusieurs problèmes :
- un import JUnit invalide ;
- des références à Mockito non résolues ;
- des appels avec des types de paramètres incorrects ;
- des assertions ne correspondant pas au comportement réel
  de la méthode, notamment sur la distinction majuscules/minuscules.

Le message `BUILD SUCCESS` du plugin indiquait seulement que
la procédure de génération s'était terminée. Il ne signifiait
pas que le test généré compilait.

### 2.3 Correction et exécution

Nous avons examiné les propositions générées et retenu
7 scénarios pertinents dans le fichier :

`tika-core/src/test/java/org/apache/tika/utils/XMLReaderUtilsGeneratedTest.java`

Nous avons remplacé les simulations Mockito par des objets
`AttributesImpl`, corrigé les assertions, supprimé les appels
invalides et adapté le fichier aux règles Checkstyle du projet
(en-tête Apache, fins de ligne LF et retour à la ligne final).

Après ces corrections, la commande suivante a réussi :

    mvnw.cmd test -pl tika-core -Dtest=XMLReaderUtilsGeneratedTest

Résultat : **7 tests exécutés, 0 échec, 0 erreur**.

Ces observations montrent que les tests générés peuvent fournir
des idées de scénarios utiles, mais que leur compilation et la
validité de leurs oracles doivent être vérifiées manuellement.

## 3. Analyse de mutation et amélioration des tests

### 3.1 Test manuel ajouté

Après l'analyse initiale, nous avons ajouté un test JUnit
`testGetAttrValue()` dans `XMLReaderUtilsTest.java`.

Ce test vérifie deux situations :
- lorsqu'un attribut XML nommé `nom` existe, la méthode
  retourne la valeur attendue `Taha` ;
- lorsqu'un attribut est absent, la méthode retourne `null`.

Ces assertions constituent des oracles explicites fondés sur
le comportement attendu de `getAttrValue()`.

Le test manuel a permis de tuer quatre mutants supplémentaires :

| Ligne | Mutateur PITest | Comportement modifié |
|---|---|---|
| 1032 | NegateConditionalsMutator | Inversion de la condition de boucle |
| 1033 | NegateConditionalsMutator | Inversion de la comparaison du nom |
| 1034 | EmptyObjectReturnValsMutator | Remplacement de la valeur par une chaîne vide |
| 1037 | EmptyObjectReturnValsMutator | Remplacement de `null` par une chaîne vide |

Le test permet de détecter ces modifications parce qu'il
vérifie à la fois une valeur présente et une valeur absente.

### 3.2 Comparaison des résultats PITest

| Configuration | Mutants tués | Score | Mutants sans couverture |
|---|---:|---:|---:|
| Tests initiaux | 21 / 181 | 12 % | 77 |
| Tests initiaux et test manuel | 25 / 181 | 14 % | 72 |
| Tests initiaux, manuel et IA corrigés | 23 / 181 | 13 % | 72 |

Les trois rapports sont conservés dans :
- [`pitest-initial/`](pitest-initial/index.html)
- [`pitest-manuel/`](pitest-manuel/index.html)
- [`pitest-combine/`](pitest-combine/index.html)

Nous avons répété l'analyse combinée et obtenu deux fois
23 mutants tués sur 181.

Deux mutants auparavant tués sont devenus survivants :
- `getDocumentBuilder()`, ligne 290 : retour remplacé par `null` ;
- `getDocumentBuilderFactory()`, ligne 271 : retour remplacé par `null`.

La cause exacte de cette variation n'a pas été établie.
Nous ne pouvons donc pas affirmer que les tests générés
ont provoqué cette diminution.

Les sept tests IA corrigés passent tous avec JUnit.
Cependant, ils n'ont pas démontré de gain supplémentaire
de mutation dans notre analyse combinée.

### 3.3 Mutants survivants et limites

Un mutant de `getAttrValue()` à la ligne 1032,
produit par `ConditionalsBoundaryMutator`, demeure survivant.

Ce mutant modifie la condition limite d'une boucle.
Il illustre une limite de nos tests : une modification
interne peut ne pas produire de différence observable
dans les résultats vérifiés.

D'autres mutants de `XMLReaderUtils` restent sans couverture,
notamment dans certaines méthodes de construction et de
configuration des parseurs XML. Nous avons concentré nos
corrections sur `getAttr

## 4. Liens vers le travail réalisé

**Dépôt GitHub du binôme :**  
https://github.com/azamitaha/tika-ift3913-tache2/tree/tache2-tests

**Rapport complet (README) :**  
https://github.com/azamitaha/tika-ift3913-tache2/blob/tache2-tests/README.md

**Code des tests générés avec ChatUniTest et corrigés :**  
[tika-core/src/test/java/org/apache/tika/utils/XMLReaderUtilsGeneratedTest.java](tika-core/src/test/java/org/apache/tika/utils/XMLReaderUtilsGeneratedTest.java)

**Code des tests existants et du test manuel :**  
[tika-core/src/test/java/org/apache/tika/utils/XMLReaderUtilsTest.java](tika-core/src/test/java/org/apache/tika/utils/XMLReaderUtilsTest.java)