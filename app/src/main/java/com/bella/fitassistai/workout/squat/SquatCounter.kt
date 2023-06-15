package com.bella.fitassistai.workout.squat

import com.bella.fitassistai.data.BodyPart
import com.bella.fitassistai.data.Person
import com.bella.fitassistai.workout.WorkoutCounter

class SquatCounter : WorkoutCounter() {

    override fun countAlgorithm(person : Person) : Int
    {
        if (person.keyPoints[BodyPart.NOSE.ordinal].score >= MIN_CONFIDENCE) {
            var y = 1000 - person.keyPoints[BodyPart.NOSE.ordinal].coordinate.y
            var dy = y - prev_y
            if (!first) {
                if (bottom != 0 && top != 0) {
                    if (goal == 1 && dy > 0 && (y - bottom) > (top - bottom) * REP_THRESHOLD) {
                        if (top - bottom > MIN_AMPLITUDE) {
                            count++
                            goal = -1
                        }
                    }
                    else if (goal == -1 && dy < 0 && (top - y) > (top - bottom) * REP_THRESHOLD) {
                        goal = 1
                    }
                }

                if (dy < 0 && prev_dy >= 0 && prev_y - bottom > MIN_AMPLITUDE) {
                    top = prev_y
                }
                else if (dy > 0 && prev_dy <= 0 && top - prev_y > MIN_AMPLITUDE) {
                    bottom = prev_y
                }
            }

            first = false
            prev_y = y.toInt()
            prev_dy = dy.toInt()
        }

        return count
    }
}