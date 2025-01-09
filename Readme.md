# Project structure
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── chuchro/
│   │           └── towerdefence/
│   │               ├── Game.java                 # Main game class
│   │               ├── gameobjects/              # Game entities
│   │               │   ├── Enemy.java
│   │               │   ├── Tower.java
│   │               │   └── GameObject.java       # Base class
│   │               ├── ui/                       # UI components
│   │               │   ├── GameMenu.java
│   │               │   ├── MainMenu.java
│   │               │   ├── PauseMenu.java
│   │               │   └── TowerMenu.java
│   │               ├── managers/                 # Game systems
│   │               │   ├── GameManager.java      # Game state & loop
│   │               │   ├── RenderManager.java    # Graphics rendering
│   │               │   └── ResourceManager.java  # Assets & resources
│   │               └── utils/                    # Helper classes
│   │                   ├── Point2D.java
│   │                   └── Constants.java
│   └── resources/
└── styles/
    └── styles.css