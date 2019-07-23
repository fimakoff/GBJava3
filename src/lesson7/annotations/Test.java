package lesson7.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Test {
    enum Priority {
        VERYVERYLOWLOW(1),
        VERYLOWLOW(2),
        LOWLOW(3),
        LOW(4),
        MEDIUMLOW(5),
        MEDIUMHIGH(6),
        HIGH(7),
        HIGHHIGH(8),
        VERYHIGHHIGH(9),
        VERYVERYHIGHHIGH(10);

        private int priority;

        Priority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    Priority priority() default Priority.MEDIUMLOW;
}
