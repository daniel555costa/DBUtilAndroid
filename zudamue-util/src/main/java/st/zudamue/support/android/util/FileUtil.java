package st.zudamue.support.android.util;

import java.io.File;

/**
 * Created by siie2 on 8/26/17.
 */

/**
 * Gestor de ficheiro
 */
public class FileUtil {

    public static int deleteRecursive( File file ){
        if( file == null ) return 0;
        if( ! file.exists() ) return 0;
        if( file.isFile() ) {
            if( file.delete() )
                return 1;
            else return 0;
        }
        if( file.isDirectory() ){

            int total = 0;
            File[] files = file.listFiles();
            if( files != null ){
                for ( File child : file.listFiles() ){
                    total += FileUtil.deleteRecursive( child );
                }
                if( file.delete() ) return total + 1;
                else return total;
            }

        }
        return 0;
    }
}
