package lesson7;

import lesson7.annotations.AfterSuite;
import lesson7.annotations.BeforeSuite;
import lesson7.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class MainTestClass {
    public static void main(String[] args) {
        try {
            start(Tests.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start(Class<?> initClass)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException{
        Constructor constructor = initClass.getConstructor();
        Object sampleClass = constructor.newInstance();
        /*
        *   Для запуска тестов в метод startTests() скармливаем класс с тестами
        *   и новый конструктор класса.
        *   startTests() имеет перегруженную версию.
        *   Если в startTests() третьим аргументом передать какую-либо аннотацию,
        *   то ее использование будет ограничено одним разом.
        *   Если переданный тест использовать больше чем один раз, то тут как
        *   тут образуется RuntimeException.
         */
        startTests(initClass, sampleClass, BeforeSuite.class);

        startTests(initClass, sampleClass);

        startTests(initClass, sampleClass, AfterSuite.class);

    }

    private static void startTests(Class<?> initClass, Object clazz)
            throws IllegalAccessException, InvocationTargetException {
        List<Method> methodsOfTest = getMethods(initClass, Test.class);

        methodsOfTest.sort(Comparator.comparingInt((ToIntFunction<Method>)
                m -> m.getAnnotation(Test.class).priority().getPriority()).reversed());

        for (Method method : methodsOfTest) {
            method.invoke(clazz);
        }
    }

    private static void startTests(Class<?> initClass,
                                   Object clazz,
                                   Class<? extends Annotation> annotations) {
        List<Method> methodList = getMethods(initClass, annotations);
        if (methodList.size() == 1) {
            try {
                methodList.get(0).invoke(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Тест " + annotations.getName()
                    + " должен использоваться только один раз");
        }
    }

    private static List<Method> getMethods(Class<?> initClass,
                                           Class<? extends Annotation> annotations) {
        List<Method> methodList = new ArrayList<>();
        for (Method method : initClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotations)) {
                methodList.add(method);
            }
        }
        return methodList;
    }
}
