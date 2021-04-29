package com.bangkit.faniabdullah_jetpack.utils

import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.*
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.*
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

object DataDummy {
    fun generateDummyDataMovieNowPlaying(): List<MovieData> {
        val listMovieNowPlaying = ArrayList<MovieData>()

        listMovieNowPlaying.add(
            MovieData(
                "1",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "2",
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",

                "2021-02-24",
                8.3,
                8,
            )
        )


        listMovieNowPlaying.add(
            MovieData(
                "3",
                "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",

                "2021-03-03",
                8.3,
                8,
            )
        )


        listMovieNowPlaying.add(
            MovieData(
                "4",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",

                "2021-04-07",
                8.3,
                8,
            )
        )


        listMovieNowPlaying.add(
            MovieData(
                "5",
                "/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg",
                "Tom & Jerry",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",

                "2021-02-11",
                8.3,
                8,
            )
        )


        listMovieNowPlaying.add(
            MovieData(
                "6",
                "/jnq4fV53Px9HvUZD2bQIxtGSwS7.jpg",
                "Twist",
                "A Dicken’s classic brought thrillingly up to date in the teeming heartland of modern London, where a group of street smart young hustlers plan the heist of the century for the ultimate payday.",

                "2021-01-22",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "7",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home",

                "2020-12-03",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "8",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",

                "2021-03-31",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "9",
                "/9Is9OrQUnKczCfsLSbsbx8YSmES.jpg",
                "Ганзель, Гретель и Агентство Магии",
                "The Secret Magic Control Agency sends its two best agents, Hansel and Gretel, to fight against the witch of the Gingerbread House.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "10",
                "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
                "Breach",
                "A hardened mechanic must stay awake and maintain an interstellar ark fleeing the dying planet Earth with a few thousand lucky souls on board... the last of humanity. Unfortunately, humans are not the only passengers. A shapeshifting alien creature has taken residence, its only goal is to kill as many people as possible. The crew must think quickly to stop this menace before it destroys mankind.",

                "2020-12-17",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "11",
                "/3ombg55JQiIpoPnXYb2oYdr6DtP.jp",
                "Skylines",
                "When a virus threatens to turn the now earth-dwelling friendly alien hybrids against humans, Captain Rose Corley must lead a team of elite mercenaries on a mission to the alien world in order to save what's left of humanity.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listMovieNowPlaying.add(
            MovieData(
                "12",
                "/d1sVANghKKMZNvqjW0V6y1ejvV9.jpg",
                "Kimetsu no Yaiba - The Movie: Mugen Train",
                "  Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!.",

                "2020-10-16",
                8.3,
                8,
            )
        )

        return listMovieNowPlaying
    }

    fun generateDummyDataTvShowsPopular(): List<MovieData> {
        val listTvShowsPopular = ArrayList<MovieData>()
        listTvShowsPopular.add(
            MovieData(
                "111",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "222",
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",

                "2017-09-25",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "333",
                "/z59kJfcElR9eHO9rJbWp4qWMuee.jp",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",

                "2014-10-07",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "444",
                "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "555",
                "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",

                "2005-03-27",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "666",
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",

                "2021-03-24",
                8.3,
                8,
            )
        )


        listTvShowsPopular.add(
            MovieData(
                "777",
                "/pLG4ihU1d2XkQbASQDjsFu9U7d9.jpg",
                "Who Killed Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",

                "2021-03-24",
                8.3,
                8,
            )
        )


        listTvShowsPopular.add(
            MovieData(
                "888",
                "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",

                "2016-01-25",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "999",
                "/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",

                "2021-03-24",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "1010",
                "//gmbsR4SvYhhj4SvLAlTKxIkFxp9.jpg",
                "Superman & Lois",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",

                "2021-02-23",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "1111",
                "/57vVjteucIF3bGnZj6PmaoJRScw.jpg",
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",

                "2021-01-15",
                8.3,
                8,
            )
        )

        listTvShowsPopular.add(
            MovieData(
                "1212",
                "/y0A3EFeGJhzKS4Zyy3LilbwDFvV.jpg",
                "Transformers: War for Cybertron: Siege",
                "On their dying planet, the Autobots and Decepticons battle fiercely for control of the AllSpark in the Transformers universe's origin story.",

                "2020-07-30",
                8.3,
                8,
            )
        )

        return listTvShowsPopular
    }

    fun generateDummyDataRemoteNowPlaying(): List<MovieResponse> {
        return listOf(
            MovieResponse(
                adult = false,
                backdropPath = "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
                genreIds = listOf(10749),
                id = 724089,
                originalLanguage = "en",
                originalTitle = "Gabriel's Inferno Part II",
                overview = "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
                popularity = 12.613,
                posterPath = "/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
                releaseDate = "2020-07-31",
                title = "Gabriel's Inferno Part II",
                video = false,
                voteAverage = 8.7,
                voteCount = 1240
            ),
            MovieResponse(
                adult = false,
                backdropPath = "/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
                genreIds = listOf(
                    18,
                    80
                ),
                id = 238,
                originalLanguage = "en",
                originalTitle = "The Godfather",
                overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                popularity = 45.469,
                posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                releaseDate = "1972-03-14",
                title = "The Godfather",
                video = false,
                voteAverage = 8.7,
                voteCount = 14196
            ),
            MovieResponse(
                adult = false,
                backdropPath = "/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg",
                genreIds = listOf(
                    18,
                    80
                ),
                id = 278,
                originalLanguage = "en",
                originalTitle = "The Shawshank Redemption",
                overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                popularity = 40.104,
                posterPath = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                releaseDate = "1994-09-23",
                title = "The Shawshank Redemption",
                video = false,
                voteAverage = 8.7,
                voteCount = 18799
            )
        )

    }

    fun generateDummyDataRemoteTvShowPopular(): List<TvShowsResponse> {
        return listOf(
            TvShowsResponse(
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                firstAirDate = "2021-03-19",
                genreIds = listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                id = 88396,
                name = "The Falcon and the Winter Soldier",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Falcon and the Winter Soldier",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                popularity = 2640.049,
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                voteAverage = 7.9,
                voteCount = 4994
            ),
            TvShowsResponse(
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                firstAirDate = "2017-09-25",
                genreIds = listOf(18),
                id = 71712,
                name = "The Good Doctor",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Good Doctor",
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                popularity = 1548.3,
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                voteAverage = 8.6,
                voteCount = 8153
            ),
            TvShowsResponse(
                backdropPath = "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                firstAirDate = "2016-01-25",
                genreIds = listOf(
                    80,
                    10765
                ),
                id = 63174,
                name = "Lucifer",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Lucifer",
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                popularity = 748.564,
                posterPath = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                voteAverage = 8.5,
                voteCount = 8378
            )
        )
    }

    fun generateDummyDataDetailMovie(): DetailMovieResponse {
        return DetailMovieResponse(
            adult = false,
            backdropPath = "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
            belongsToCollection = BelongsToCollection(
                id = 729322,
                name = "Gabriel's Inferno Collection",
                posterPath = "/LdSn17U6ybhtPJT3S6fTNRni5Y.jpg",
                backdropPath = "/hXF55codODfnzTZDExbUbfFmA9y.jpg"
            ),
            budget = 0,
            genres = listOf(
                GenresItem(
                    id = 10749,
                    name = "Romance"
                ),
            ),
            homepage = "https://watch.passionflix.com/watch/f299fa17-5a2b-4fee-b53a-a4651747431b",
            id = 724089,
            imdbId = "tt13535454",
            originalLanguage = "en",
            originalTitle = "Gabriel's Inferno Part II",
            overview = "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
            popularity = 12.613,
            posterPath = "/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
            productionCompanies = listOf(
                ProductionCompaniesItem(
                    id = 92153,
                    logoPath = "/psjvYkjjgAPtS8utnFYDM8t8yi7.png",
                    name = "PassionFlix",
                    originCountry = "US"
                ),
            ),
            productionCountries = listOf(),
            releaseDate = "2020-07-31",
            revenue = 0,
            runtime = 105,
            spokenLanguages = listOf(
                SpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Released",
            tagline = "",
            title = "Gabriel's Inferno Part II",
            video = false,
            voteAverage = 8.7,
            voteCount = 1240

        )
    }

    fun generateDummyDataDetailTvShow(): DetailTvResponse {
        return DetailTvResponse(
            backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            createdBy = listOf(
                CreatedByItem(
                    id = 1868712,
                    creditId = "605508e2960cde00721fc5e8",
                    name = "Malcolm Spellman",
                    gender = 2,
                    profilePath = null
                )
            ),
            episodeRunTime = listOf(50),
            firstAirDate = "2021-03-19",
            genres = listOf(
                GenresItem(
                    id = 10765,
                    name = "Sci-Fi & Fantasy"
                ),
                GenresItem(
                    id = 10759,
                    name = "Action & Adventure"
                ),
                GenresItem(
                    id = 18,
                    name = "Drama"
                ),
                GenresItem(
                    id = 10768,
                    name = "War & Politics"
                )
            ),
            homepage = "https://www.disneyplus.com/series/the-falcon-and-the-winter-soldier/4gglDBMx8icA",
            id = 88396,
            inProduction = false,
            languages = listOf(
                "en",
                "fr"
            ),
            lastAirDate = "2021-04-23",
            lastEpisodeToAir = LastEpisodeToAir(
                airDate = "2021-04-23",
                episodeNumber = 6,
                id = 2558743,
                name = "One World, One People",
                overview = "As The Flag Smashers escalate their efforts, Sam and Bucky take action.",
                productionCode = "",
                seasonNumber = 1,
                stillPath = "/qXxCqMP7aj3rGndhVfGUyyU6hyq.jpg",
                voteAverage = 6.6,
                voteCount = 5
            ),
            name = "The Falcon and the Winter Soldier",
            nextEpisodeToAir = null,
            networks = listOf(
                NetworksItem(
                    name = "Disney+",
                    id = 2739,
                    logoPath = "/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png",
                    originCountry = "US"
                )
            ),
            numberOfEpisodes = 6,
            numberOfSeasons = 1,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "The Falcon and the Winter Soldier",
            overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            popularity = 2640.049,
            posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            productionCompanies = listOf(
                ProductionCompaniesItem(
                    id = 420,
                    logoPath = "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
                    name = "Marvel Studios",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                ProductionCountriesItem(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            seasons = listOf(
                SeasonsItem(
                    airDate = "2021-03-19",
                    episodeCount = 6,
                    id = 156676,
                    name = "Season 1",
                    overview = "",
                    posterPath = "/fIT6Y6O3cUX1X8qY8pZgzEvxUDQ.jpg",
                    seasonNumber = 1
                )
            ),
            spokenLanguages = listOf(
                SpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                ),
                SpokenLanguagesItem(
                    englishName = "French",
                    iso6391 = "fr",
                    name = "Français"
                )
            ),
            status = "Ended",
            tagline = "Honor the shield.",
            type = "Miniseries",
            voteAverage = 7.9,
            voteCount = 5000
        )
    }
}