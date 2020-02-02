package classloader;

import java.io.File;
import java.io.IOException;
import util.UtilMenu;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;
import javassist.NotFoundException;

public class SampleLoader extends ClassLoader {
   static String WORK_DIR = System.getProperty("user.dir");
   static String INPUT_DIR = WORK_DIR + File.separator + "classfiles";
   static String TARGET_APP = "MyApp";
   private ClassPool pool;

   public static void main(String[] args) throws Throwable {
		while (true) {			
			UtilMenu.showMenuOptions();
			String[] inputs = UtilMenu.getArguments();
			if (inputs.length == 2) {
				try {
				TARGET_APP = inputs[0];
				SampleLoader loader = new SampleLoader();
				Class<?> f = loader.findClass(TARGET_APP, inputs[1]);
				// Class<?> c = loader.loadClass(TARGET_APP);
				f.getDeclaredMethod("main", new Class[] { String[].class }). //
						invoke(null, new Object[] { inputs });
			}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("[WRN] Invalid Input.");
			}


		}
   
   }

   public SampleLoader() throws NotFoundException {
      pool = new ClassPool();
      pool.insertClassPath(INPUT_DIR); // Search MyApp.class in this path.
   }

   /* 
    * Find a specified class, and modify the bytecode.
    */
   protected Class<?> findClass(String name, String fieldName) throws ClassNotFoundException {
      try {
         CtClass cc = pool.get(name);
         if (name.equals(name)) {
            CtField f = new CtField(CtClass.doubleType, fieldName, cc);
            f.setModifiers(Modifier.PUBLIC);
            cc.addField(f, CtField.Initializer.constant(1.2));
         }
         byte[] b = cc.toBytecode();
         return defineClass(name, b, 0, b.length);
      } catch (NotFoundException e) {
         throw new ClassNotFoundException();
      } catch (IOException e) {
         throw new ClassNotFoundException();
      } catch (CannotCompileException e) {
         throw new ClassNotFoundException();
      }
   }
}
