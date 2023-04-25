package com.croumy.nbascores.presentation.data

enum class TeamPicture {
    ATL {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-atlanta-hawks-logo.png"
    },
    BKN {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-brooklyn-nets-logo.png"
    },
    BOS {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-boston-celtics-logo.png"
    },
    CEL {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-celtics-logo.png"
    },
    CHA {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-charlotte-hornets-logo.png"
    },
    CHI {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-chicago-bulls-logo.png"
    },
    CLE {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-cleveland-cavaliers-logo.png"
    },
    DAL {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-dallas-mavericks-logo.png"
    },
    DEN {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-denver-nuggets-logo-2018.png"
    },
    DET {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-detroit-pistons-logo.png"
    },
    GSW {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-golden-state-warriors-logo.png"
    },
    HOU {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-houston-rockets-logo.png"
    },
    IND {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-indiana-pacers-logo.png"
    },
    LAC {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-la-clippers-logo.png"
    },
    LAL {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-los-angeles-lakers-logo.png"
    },
    MEM {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-memphis-grizzlies-logo.png"
    },
    MIA {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-miami-heat-logo.png"
    },
    MIL {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-milwaukee-bucks-logo.png"
    },
    MIN {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-minnesota-timberwolves-logo.png"
    },
    NOP {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-new-orleans-pelicans-logo.png"
    },
    NYK {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-new-york-knicks-logo.png"
    },
    OKC {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-oklahoma-city-thunder-logo.png"
    },
    ORL {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-orlando-magic-logo.png"
    },
    PHI {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-philadelphia-76ers-logo.png"
    },
    PHX {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-phoenix-suns-logo.png"
    },
    SAC {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-sacramento-kings-logo.png"
    },
    SAS {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-san-antonio-spurs-logo.png"
    },
    TOR {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-toronto-raptors-logo.png"
    },
    UTA {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-utah-jazz-logo.png"
    },
    WAS {
        override val logo: String = "https://loodibee.com/wp-content/uploads/nba-washington-wizards-logo.png"
    };

    abstract val logo: String
}