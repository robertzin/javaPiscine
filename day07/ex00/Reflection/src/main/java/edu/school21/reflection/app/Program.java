package edu.school21.reflection.app;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.*;
import java.util.*;

public class Program {
    public static void main(String[] args){
        String packageName = "edu.school21.reflection.models";
        ArrayList<Class<?>> classes = getAllClasses(packageName);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Classes:");
        for (Class<?> some: classes)
            System.out.println(some.getSimpleName());

        System.out.println("---------------------");
        System.out.println("Enter class name:");

        String className = scanner.nextLine();
        Class<?> toOperate = getExactClass(classes, className);
        ArrayList<Field> fields = printFields(getFields(toOperate));
        ArrayList<Method> methods = printMethods(getMethods(toOperate));

        System.out.println("---------------------");
        System.out.println("Let's create an object.");

        Object object = null;
        Class<?>[] myClassParams = getConstructors(toOperate);
        try {
            if (myClassParams != null) {
                object = createCustomClass(toOperate, myClassParams, scanner);
            } else {
                object = createDefaultClass(toOperate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Object created: " + object.toString());
        System.out.println("---------------------");
        changeFieldValue(object, scanner);
        System.out.println("Object updated: " + object.toString());
        System.out.println("---------------------");
        System.out.println("Method returned: \n" + methodToCall(object, scanner));
    }

    public static ArrayList<Class<?>> getAllClasses(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new ArrayList<>(reflections.getSubTypesOf(Object.class));
    }

    public static Class<?> getExactClass(ArrayList<Class<?>> classes, String className) {
        Class<?> toOperate = null;

        for (Class<?> someClass: classes) {
            if (someClass.getSimpleName().equals(className))
                toOperate = someClass;
        }
        if (toOperate == null)
            throw new RuntimeException("ClassNotFound");
        return toOperate;
    }

    public static ArrayList<Field> getFields(Class<?> someClass) {
        return new ArrayList<>(Arrays.asList(someClass.getDeclaredFields()));
    }

    public static ArrayList<Method> getMethods(Class<?> someClass) {
        Method[] parentMethods = someClass.getSuperclass().getMethods();
        Method[] childMethods = someClass.getDeclaredMethods();
        ArrayList<Method> childList = new ArrayList<>();

        for (Method method : childMethods) {
            int flag = 0;
            for (Method parentMethod : parentMethods) {
                if (parentMethod.getName().equals(method.getName()))
                    flag = 1;
            }
            if (flag == 0)
                childList.add(method);
        }
        return childList;
    }

    public static ArrayList<Field> printFields(ArrayList<Field> fields) {
        System.out.println("fields :");
        for (Field field : fields) {
            System.out.print("\t" + field.getType().getSimpleName() + " " + field.getName() + "\n");
        }
        return fields;
    }

    public static ArrayList<Method> printMethods(ArrayList<Method> methods) {
        System.out.println("methods :");
        for (Method method : methods) {
            Class<?>[] parameters = method.getParameterTypes();
            String toPrint = ("\t"
                    + method.getReturnType().getSimpleName()
                    + " " + method.getName() + "("
                    + Arrays.toString(parameters)
                    + ")\n");
            toPrint = toPrint.replaceAll("[\\[\\]]", "");
            System.out.println(toPrint);
        }
        return methods;
    }

    public static Class<?>[] getConstructors(Class<?> someClass) {
        Constructor<?>[] constructors = someClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterTypes().length != 0)
                return (constructor.getParameterTypes());
        }
        return null;
    }

    public static Object createDefaultClass(Class<?> toOperate) {
        try {
            Class<?> myClass = Class.forName(toOperate.getName());
            Constructor<?> defaultConstructor = myClass.getConstructor();
            return myClass.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static Object createCustomClass(Class<?> toOperate, Class<?>[] myClassParams, Scanner scanner) {
        Object[] params = new Object[myClassParams.length];
        Field[] fields = getFields(toOperate).toArray(new Field[0]);
        try {
            Class<?> myClass = Class.forName(toOperate.getName());
            for (int i = 0; i < myClassParams.length; i++) {
                System.out.println(fields[i].getName());
				params[i] = getValue(fields[i].getType().getSimpleName(), scanner);
            }
            return myClass.getConstructor(myClassParams).newInstance(params);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

	public static Object getValue(String simpleClassName, Scanner scanner) {
		switch(simpleClassName) {
			case "int":
				return Integer.parseInt(scanner.nextLine());
			case "double":
				return Double.parseDouble(scanner.nextLine());
			case "long":
				return Long.parseLong(scanner.nextLine());
			case "String":
				return scanner.nextLine();
			case "boolean":
				return Boolean.parseBoolean(scanner.nextLine());
			default:
				return null;
		}
	}

    public static void changeFieldValue(Object myClass, Scanner scanner) {
        System.out.println("Enter name of the field for changing:");
        String fieldToChange = scanner.nextLine();
        System.out.println("Enter String value:");
        String value = scanner.nextLine();

        try {
            Field field = myClass.getClass().getDeclaredField(fieldToChange);
            field.setAccessible(true);
            field.set(myClass, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int methodToCall(Object myClass, Scanner scanner) {
        System.out.println("Enter name of the method for call:");
        String methodToCall = scanner.nextLine();
        System.out.println("Enter int value:");
        int value = scanner.nextInt();
        int ret = 0;

        try {
            Method[] methods = myClass.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if (method.getName().equals(methodToCall.substring(0, methodToCall.indexOf('(')))) {
                    ret = (int) method.invoke(myClass, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
