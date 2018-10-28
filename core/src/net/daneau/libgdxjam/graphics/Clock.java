package net.daneau.libgdxjam.graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.daneau.libgdxjam.utils.StringNumber;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class Clock {

    private static final String SEPARATOR = ":";
    private BitmapFont font;
    private int minutes;
    private int hours;
    private float timeCount;
    private Period period;

    public Clock(BitmapFont font) {
        this.font = font;
    }

    public void setTime(int hour, int minute, Period period) {
        this.hours = hour;
        this.minutes = minute;
        this.period = period;
    }

    public void update(float delta) {
        this.timeCount += delta;

        if (this.timeCount >= 1f) {
            ++this.minutes;

            if (this.minutes == 60) {
                ++this.hours;
                this.minutes = 0;

                if (this.hours == 12) {
                    this.period = (this.period == Period.PM) ? Period.AM : Period.PM;
                }
            }
            this.timeCount -= 1f;

        }
    }

    //Yep, it's ugly, but it's avoiding string creation
    public void draw(SpriteBatch spriteBatch) {
        if (this.hours < 10) {
            this.font.draw(spriteBatch, StringNumber.get(0), 30, 50);
            this.font.draw(spriteBatch, StringNumber.get(this.hours), 60, 50);
        } else {
            this.font.draw(spriteBatch, StringNumber.get(this.hours), 30, 50);
        }

        this.font.draw(spriteBatch, SEPARATOR, 90, 55);

        if (this.minutes < 10) {
            this.font.draw(spriteBatch, StringNumber.get(0), 104, 50);
            this.font.draw(spriteBatch, StringNumber.get(this.minutes), 134, 50);
        } else {
            this.font.draw(spriteBatch, StringNumber.get(this.minutes), 104, 50);
        }
        this.font.draw(spriteBatch, this.period.getCode(), 174, 50);


    }

    @Override
    public String toString() {
        return (this.hours < 10 ? "0" : "") + this.hours + ":" + (this.minutes < 10 ? "0" : "") + this.minutes + " " + this.period.getCode();
    }

    public enum Period {
        AM("AM"),
        PM("PM");

        private String code;

        Period(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }

}
