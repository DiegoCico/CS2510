package uk.ac.nulondon;

public class Grid {
    static class Pixel {
        int color;
        Pixel next;

        public Pixel(int color) {
            this.color = color;
            this.next = null;
        }
    }

    static class Column {
        Pixel head;

        // Method to remove a pixel from the column
        void removePixel(int pixelIndex) {
            if (head == null) return;
            if (pixelIndex == 0) {
                head = head.next;
                return;
            }

            Pixel current = head;
            for (int i = 0; current != null && i < pixelIndex - 1; i++) {
                current = current.next;
            }

            if (current == null || current.next == null) return;

            current.next = current.next.next;
        }

        // Method to insert a pixel into the column
        void insertPixel(int pixelIndex, int color) {
            Pixel newPixel = new Pixel(color);

            if (head == null || pixelIndex == 0) {
                newPixel.next = head;
                head = newPixel;
                return;
            }

            Pixel current = head;
            for (int i = 0; current != null && i < pixelIndex - 1; i++) {
                current = current.next;
            }

            if (current == null) return;

            newPixel.next = current.next;
            current.next = newPixel;
        }
    }

    static class Image {
        Column[] columns;

        public Image(int height, int width) {
            columns = new Column[width];
            for (int i = 0; i < width; i++) {
                columns[i] = new Column();
                // Initialize each column with height pixels
                Pixel current = columns[i].head;
                for (int j = 0; j < height; j++) {
                    if (current == null) {
                        columns[i].head = new Pixel(3254324);
                        current = columns[i].head;
                    } else {
                        current.next = new Pixel(123332);
                        current = current.next;
                    }
                }
            }
        }

        // Method to remove a vertical seam
        public void removeSeam(int[] seam) {
            for (int i = 0; i < seam.length; i++) {
                columns[i].removePixel(seam[i]);
            }
        }

        // Method to insert a vertical seam
        public void insertSeam(int[] seam, int[] colors) {
            for (int i = 0; i < seam.length; i++) {
                columns[i].insertPixel(seam[i], colors[i]);
            }
        }
    }
}
