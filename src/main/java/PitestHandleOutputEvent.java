import com.intellij.debugger.engine.DebuggerUtils;
import com.intellij.execution.filters.OpenFileHyperlinkInfo;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.newvfs.NewVirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import javafx.scene.control.Hyperlink;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Karol on 04.02.2017.
 */
public class PitestHandleOutputEvent {

    static public Pair<String,Integer> paraseHyperlink(Hyperlink hyperlink){

        /*Funkcja parasująca text z hypertext, rozdzielająca go na
            nazwę klasy którą otworzyć trzeba oraz numer wiersza
            Z tym że nie wiem jak u nas w naszej skromnej wtyczce
            mają wyglącać hyperlinki do kodu
        */

        return new Pair<>(hyperlink.getText(),0);
    }

    static public void goToCodeLine(String filename, int lineNumber){

        GlobalSearchScope searchScope = new GlobalSearchScope() {
            @Override
            public int compare(@NotNull VirtualFile file1, @NotNull VirtualFile file2) {
                return 0;
            }

            @Override
            public boolean isSearchInModuleContent(@NotNull Module aModule) {
                return false;
            }

            @Override
            public boolean isSearchInLibraries() {
                return false;
            }

            @Override
            public boolean contains(@NotNull VirtualFile file) {
                return false;
            }
        };

        Project project = searchScope.getProject();

        if (project == null){

            Object findClass = DebuggerUtils.findClass(filename,project,searchScope);

            HyperlinkVirtualFile virtualFile = new HyperlinkVirtualFile();

            virtualFile.setObjectClass(findClass);

            OpenFileHyperlinkInfo openFileHyperlinkInfo = new OpenFileHyperlinkInfo(project,virtualFile,lineNumber);

            openFileHyperlinkInfo.navigate(project);
        }
    }

    static class HyperlinkVirtualFile extends VirtualFile{

        @NotNull
        @Override
        public String getName() {
            return null;
        }

        @NotNull
        @Override
        public VirtualFileSystem getFileSystem() {
            return null;
        }

        @NotNull
        @Override
        public String getPath() {
            return null;
        }

        @Override
        public boolean isWritable() {
            return false;
        }

        @Override
        public boolean isDirectory() {
            return false;
        }

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public VirtualFile getParent() {
            return null;
        }

        @Override
        public VirtualFile[] getChildren() {
            return new VirtualFile[0];
        }

        @NotNull
        @Override
        public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws IOException {
            return null;
        }

        @NotNull
        @Override
        public byte[] contentsToByteArray() throws IOException {
            return new byte[0];
        }

        @Override
        public long getTimeStamp() {
            return 0;
        }

        @Override
        public long getLength() {
            return 0;
        }

        @Override
        public void refresh(boolean asynchronous, boolean recursive, @Nullable Runnable postRunnable) {

        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        public void setObjectClass(Object object){

        }
    }

}
