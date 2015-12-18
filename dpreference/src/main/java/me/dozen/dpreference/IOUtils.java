package me.dozen.dpreference;


import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

final class IOUtils {

    // NOTE: This class is focussed on InputStream, OutputStream, Reader and
    // Writer. Each method should take at least one of these as a parameter,
    // or return one of them.

    private static final int EOF = -1;

    /**
     * The default buffer size ({@value}) to use for {@link
     * #copyLarge(java.io.InputStream, java.io.OutputStream)} and {@link
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    public static void closeQuietly(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void closeQuietly(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void closeQuietly(Reader r) {
        if (r != null) {
            try {
                r.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static int copy(InputStream input, OutputStream output) throws
            IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    public static long copyLarge(InputStream input, OutputStream output,
                                 byte[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 压缩一个文件或文件夹下的所有文件成一个gzip
     *
     * @param out
     * @param f
     * @param base
     * @throws Exception
     */
    public static void zip(final ZipOutputStream out, final File f, String base, final FileFilter filter)
            throws IOException {

        if (base == null) {
            base = "";
        }
        FileInputStream in = null;
        try {
            if (f.isDirectory()) {
                File[] fl;
                if (filter != null) {
                    fl = f.listFiles(filter);
                } else {
                    fl = f.listFiles();
                }
                out.putNextEntry(new ZipEntry(base + File.separator));
                base = TextUtils.isEmpty(base) ? "" : base + File.separator;

                for (int i = 0; i < fl.length; i++) {
                    zip(out, fl[i], base + fl[i].getName(), null);
                }

                File[] dirs = f.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isDirectory();
                    }
                });
                if (dirs != null) {
                    for (File subFile : dirs) {
                        zip(out, subFile, base + File.separator + subFile.getName(), filter);
                    }
                }
            } else {
                if (!TextUtils.isEmpty(base)) {
                    out.putNextEntry(new ZipEntry(base));
                } else {
                    final Date date = new Date();
                    out.putNextEntry(new ZipEntry(String.valueOf(date.getTime()) + ".txt"));
                }
                in = new FileInputStream(f);

                int bytesRead = -1;
                final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } catch (final IOException e) {
            Log.e("IOUtils", "zipFiction failed with exception:" + e.toString());
        } finally {
            closeQuietly(in);
        }
    }

}
