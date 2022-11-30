package com.coding_challenge;

import java.util.*;

public class CardGame {
    private class Deck {
        int[] cards;
        int pos; // the next card position;

        public Deck(int totalCards) {
            this.cards = new int[totalCards];
            for (int i=0; i<totalCards; i++) {
                cards[i] = i + 1;
            }
            this.pos = 0;
        }

        public void shuffle() {
            // shuffle the cards array
            // for cards[i], get a random index between [i, n-1]
            // then swap card[i] with card[index]
            // loop from 0 to n-1
            for (int i=0; i<cards.length - 1; i++) {
                int idx = getRandom(i, cards.length);

                // swap
                int temp = cards[i];
                cards[i] = cards[idx];
                cards[idx] = temp;
            }

            this.pos = 0;
        }

        private int getRandom(int min, int max) {
            Random rand = new Random();
            return rand.nextInt(max - min) + min; // [0, max-min) + min => [min, max)
        }

        public int draw() throws Exception {
            if (isEmpty()) {
                throw new Exception("no more cards!");
            }

            return cards[pos++];
        }

        public boolean isEmpty() {
            return getCardLeftCount() == 0;
        }

        public int getCardLeftCount() {
            return cards.length - pos;
        }
    }

    public void play2() throws Exception {
        Deck deck = new Deck(52);
        int p1win = 0;
        int p2win = 0;

        for (int i=0; i<5000; i++) {
            deck.shuffle();

            int score1 = 0;
            int score2 = 0;
            int maxCard1 = 0;
            int maxCard2 = 0;
            while (deck.getCardLeftCount() >= 2) {
                int card1 = deck.draw();
                int card2 = deck.draw();
                maxCard1 = Math.max(maxCard1, card1);
                maxCard2 = Math.max(maxCard2, card2);

                if (card1 > card2) {
                    score1++;
                } else if (card1 < card2) {
                    score2++;
                } else {
                    if (maxCard1 > maxCard2) {
                        score1++;
                    } else {
                        score2++;
                    }
                }
            }
            if (score1 > score2) {
                p1win++;
            } else if (score1 < score2) {
                p2win++;
            } else {
                // break the tie
                if (maxCard1 > maxCard2) {
                    p1win++;
                } else {
                    p2win++;
                }
            }
        }

        System.out.println("p1 win: " + p1win + " p2 win: " + p2win);
    }

    public void play(int players) throws Exception {
        Deck deck = new Deck(52);
        int[] wins = new int[players];

        for (int ct=0; ct<10000; ct++) {
            int[] scores = new int[players];
            deck.shuffle();
            int[] maxCards = new int[players];
            // left card count >= players to play 1 round
            while (deck.getCardLeftCount() >= players) {
                // play this round
                int[] cards = new int[players];
                for (int i = 0; i < players; i++) {
                    cards[i] = deck.draw();
                    maxCards[i] = Math.max(maxCards[i], cards[i]);
                }

                // get the winner of this round
                int winner = getWinner(cards, maxCards);
                scores[winner]++;
            }

            // follow up -> 剩余的牌随机发给几名玩家，玩最后一局
            // 利用Deck类来随机取得player的index
            Deck deckPlayers = new Deck(players);
            deckPlayers.shuffle();
            int[] scoresLeft = new int[players];
            while (!deck.isEmpty()) {
                int idx = deckPlayers.draw() - 1; // get the next random player index
                // give the card this player
                int card = deck.draw();
                scoresLeft[idx] = card;
                maxCards[idx] = Math.max(maxCards[idx], card);
            }
            int winnerLastRound = getWinner(scoresLeft, maxCards);
            scores[winnerLastRound]++;


            wins[getWinner(scores, maxCards)]++;
        }

        System.out.println(Arrays.toString(wins));
    }

    private int getWinner(int[] scores, int[] maxCards) {
        int maxScore = 0;
        int winner = -1; // winner index
        int maxCard = 0; // max card of the winner

        for (int i=0; i<scores.length; i++) {
            if (maxScore < scores[i]) {
                maxScore = scores[i];
                winner = i;
                maxCard = maxCards[i];
            } else if (maxScore == scores[i]) {
                // break the tie
                if (maxCard < maxCards[i]) {
                    // change the winner
                    winner = i;
                    maxCard = maxCards[i];
                }
            }
        }

        return winner;
    }

    public static void test() {
        CardGame game = new CardGame();
        try {
//            game.play2();
            game.play(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
