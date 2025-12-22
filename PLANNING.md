# 🧪 Parcours TDD Java & Spring (≈ 20–22h)

Objectifs :
- Pratiquer le **TDD** de manière rigoureuse
- Apprendre la **façon Java**
- Comprendre **Spring comme outil d’assemblage**
- Faire des ponts explicites avec PHP / Rails / TypeScript

Durée d’un créneau : **~1h30**  
Règle : **un créneau = un objectif clair + un livrable testable**

---

## 1️⃣ Projet 1 — Pomodoro Timer (Java pur)

🎯 Objectif global : Java propre, immutabilité, gestion du temps, machine à états  
⚠️ Aucun Spring dans ce projet

---

### 🕐 Créneau 1 — Modélisation & premier test

**Objectif**
- Modéliser un Pomodoro sans notion de temps réel

**À faire**
- Créer :
  - `Pomodoro`
  - `PomodoroState` (`WORK`, `PAUSED`, `STOPPED`)
- Écrire le premier test :
  - `shouldStartPomodoro()`

**Concepts**
- `enum`
- immutabilité (`final`)
- structure Arrange / Act / Assert (JUnit 5)

**Livrable**
- Un Pomodoro qui démarre et expose son état

**Prompt pour reprendre**
> J’ai terminé le créneau 1 du Pomodoro.  
> J’ai un test `shouldStartPomodoro()` et une première implémentation de `Pomodoro`.  
> Peux-tu relire la modélisation et me proposer des améliorations Java idiomatiques avant d’introduire le temps ?

---

### 🕐 Créneau 2 — Gestion du temps testable

**Objectif**
- Introduire le temps sans dépendre du temps réel

**À faire**
- Introduire `Clock`
- Utiliser `Instant` et `Duration`
- Écrire le test :
  - `workSessionExpiresAfter25Minutes`

**Concepts**
- `Clock.fixed`
- éviter `new Date()` / `Instant.now()` direct
- code déterministe

**Livrable**
- Un Pomodoro qui expire correctement après 25 minutes

**Prompt pour reprendre**
> J’ai injecté un `Clock` dans mon Pomodoro et écrit des tests sur l’expiration du temps.  
> Est-ce que mon approche est idiomatique Java ?  
> Peux-tu m’aider à simplifier ou renforcer les tests ?

---

### 🕐 Créneau 3 — Règles métier & transitions d’état

**Objectif**
- Rendre les règles métier explicites et impossibles à violer

**À faire**
- Interdire :
  - pause avant start
  - start deux fois
- Lever des exceptions métier

**Concepts**
- Exceptions unchecked
- `IllegalStateException`
- machine à états

**Livrable**
- Un Pomodoro robuste face aux mauvais usages

**Prompt pour reprendre**
> J’ai ajouté des règles métier et des exceptions dans le Pomodoro.  
> Peux-tu vérifier que les règles sont au bon endroit et que les exceptions sont pertinentes en Java ?

---

### 🕐 Créneau 4 — Historique & refactoring

**Objectif**
- Enregistrer l’historique des sessions

**À faire**
- Introduire `PomodoroSession`
- Tester l’historique
- Refactor du code

**Concepts**
- Value Objects
- collections immutables
- lisibilité du domaine

**Livrable**
- Historique fiable des sessions Pomodoro

**Prompt pour reprendre**
> Le Pomodoro est fonctionnel et testé.  
> Peux-tu m’aider à faire un refactoring orienté lisibilité et expressivité du domaine Java ?

---

## 2️⃣ Projet 2 — URL Shortener (Spring Boot)

🎯 Objectif global : comprendre Spring, DI, tests d’intégration  
⚠️ Le métier reste indépendant de Spring au début

---

### 🕐 Créneau 5 — Service métier (sans Spring)

**Objectif**
- Construire le cœur métier hors framework

**À faire**
- `UrlShortenerService`
- Interface `ShortCodeGenerator`
- Tests unitaires avec Mockito

**Concepts**
- Interfaces
- Dependency Inversion Principle
- mocks vs fakes

**Livrable**
- Service métier testé sans Spring

**Prompt pour reprendre**
> J’ai un `UrlShortenerService` testé sans Spring avec Mockito.  
> Peux-tu vérifier le découpage des responsabilités et me dire si c’est une bonne base pour Spring ?

---

### 🕐 Créneau 6 — Persistence in-memory

**Objectif**
- Introduire un repository

**À faire**
- Interface `UrlRepository`
- Implémentation in-memory
- Tests du repository

**Concepts**
- Repository pattern
- séparation métier / infra

**Livrable**
- Persistence simulée testée

**Prompt pour reprendre**
> J’ai ajouté un repository in-memory pour les URLs.  
> Peux-tu me dire si mon abstraction est correcte avant de brancher Spring ou JPA ?

---

### 🕐 Créneau 7 — Controller REST

**Objectif**
- Exposer l’API HTTP

**À faire**
- `@RestController`
- Endpoints :
  - `POST /urls`
  - `GET /{code}`
- Tests avec `MockMvc`

**Concepts**
- DTO
- mapping JSON
- tests web Spring

**Livrable**
- API REST fonctionnelle et testée

**Prompt pour reprendre**
> J’ai un controller REST avec des tests `MockMvc`.  
> Peux-tu relire mes endpoints et me dire si l’API est bien conçue côté Spring ?

---

### 🕐 Créneau 8 — Intégration Spring

**Objectif**
- Laisser Spring assembler l’application

**À faire**
- `@Service`, `@Repository`
- `@SpringBootTest`
- `@MockBean`

**Concepts**
- IoC
- tests d’intégration

**Livrable**
- Application Spring Boot assemblée proprement

**Prompt pour reprendre**
> L’application est maintenant assemblée par Spring.  
> Peux-tu m’aider à distinguer clairement tests unitaires et tests d’intégration ?

---

### 🕐 Créneau 9 — JPA & refactor

**Objectif**
- Remplacer la persistence mémoire par JPA

**À faire**
- `@Entity`
- Repository JPA
- Tests avec H2 ou Testcontainers

**Concepts**
- ORM vs ActiveRecord
- mapping JPA

**Livrable**
- URL Shortener persistant

**Prompt pour reprendre**
> J’ai branché JPA sur le projet.  
> Peux-tu relire mon mapping et m’aider à éviter les pièges classiques JPA ?

---

## 3️⃣ Projet 3 — Expense Tracker (DDD light)

🎯 Objectif global : modélisation métier riche + JPA

---

### 🕐 Créneau 10 — Langage métier & Value Objects

**Objectif**
- Construire un langage métier fort

**À faire**
- `Money`
- `ExpenseCategory`
- Tests de validation

**Concepts**
- Value Objects
- invariants métier

**Livrable**
- Objets métier fiables

**Prompt pour reprendre**
> J’ai modélisé les Value Objects (`Money`, `Category`).  
> Peux-tu vérifier que mes invariants sont bien exprimés à la Java ?

---

### 🕐 Créneau 11 — Agrégat Expense

**Objectif**
- Centraliser les règles métier

**À faire**
- `Expense`
- Règles :
  - montant > 0
  - date valide

**Concepts**
- agrégat
- encapsulation

**Livrable**
- Agrégat Expense cohérent

**Prompt pour reprendre**
> J’ai créé l’agrégat `Expense`.  
> Peux-tu m’aider à vérifier que la logique est bien au bon endroit ?

---

### 🕐 Créneau 12 — Repository & JPA

**Objectif**
- Persister le domaine

**À faire**
- Mapping JPA
- `@Embeddable` pour les Value Objects
- Tests JPA

**Concepts**
- entités vs objets valeur
- mapping avancé

**Livrable**
- Domaine persisté correctement

**Prompt pour reprendre**
> J’ai mappé mon domaine avec JPA.  
> Peux-tu m’aider à valider les choix de mapping et la stratégie de tests ?

---

### 🕐 Créneau 13 — Cas d’usage

**Objectif**
- Introduire les Application Services

**À faire**
- `CreateExpenseUseCase`
- Tests sans Spring

**Concepts**
- Use Case pattern
- orchestration métier

**Livrable**
- Cas d’usage clair et testable

**Prompt pour reprendre**
> J’ai implémenté un use case pour créer une dépense.  
> Peux-tu vérifier que la séparation domaine / application est propre ?

---

### 🕐 Créneau 14 — API REST & validations

**Objectif**
- Exposer l’application

**À faire**
- Controllers REST
- Validations (`@Valid`, `@NotNull`)
- Tests HTTP

**Concepts**
- validation
- DTO vs domaine

**Livrable**
- Expense Tracker exposé via API

**Prompt pour reprendre**
> L’API REST de l’Expense Tracker est en place.  
> Peux-tu m’aider à finaliser l’architecture et identifier les axes d’amélioration ?

---

## ✅ Résultat final

À l’issue de ce parcours :
- Tu pratiques le **TDD Java sérieusement**
- Tu comprends **Spring sans en dépendre**
- Tu sais modéliser un domaine riche en Java
- Tu fais des ponts conscients avec Rails / Laravel

---

👉 Quand tu veux commencer :  
**dis-moi “On démarre le créneau X”** et je te guiderai pas à pas.
