/*
 * inetsoft-core - StyleBI is a business intelligence web application.
 * Copyright © 2024 InetSoft Technology (info@inetsoft.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package inetsoft.util.swap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * XShortList, the inner size and array is accessable.
 *
 * @version 9.1
 * @author InetSoft Technology Corp
 */
public final class XShortList implements Cloneable, Serializable {
   /**
    * Create an instance of <tt>XShortList</tt>.
    */
   public XShortList() {
      this(10);
   }

   /**
    * Create an instance of <tt>XShortList</tt>.
    * @param size the specified initial size.
    */
   public XShortList(int size) {
      super();
      this.arr = new short[size];
   }

   /**
    * Trim the capacity of this <tt>XShortList</tt> instance to be the
    * list's current size.
    */
   public void trimToSize() {
      int osize = arr.length;

      if(size < osize) {
         short[] oarr = arr;
         arr = new short[size];
         System.arraycopy(oarr, 0, arr, 0, size);
      }
   }

   /**
    * Increase the capacity of this <tt>XShortList</tt> instance.
    * @param msize the desired minimum capacity.
    */
   public void ensureCapacity(int msize) {
      int osize = arr.length;

      if(msize > osize) {
         short[] oarr = arr;
         int nsize = (int) (osize * 1.5);

         if(nsize < msize) {
            nsize = msize;
         }

         arr = new short[nsize];
         System.arraycopy(oarr, 0, arr, 0, size);
      }
   }

   /**
    * Return the number of elements in this list.
    * @return the number of elements in this list.
    */
   public int size() {
      return size;
   }

   /**
    * Check if this list has no elements.
    * @return  <tt>true</tt> if this list has no elements; <tt>false</tt>
    * otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }

   /**
    * Check if this list contains the specified element.
    * @param elem element whose presence in this List is to be tested.
    * @return <tt>true</tt> if the specified element is present; <tt>false</tt>
    * otherwise.
    */
   public boolean contains(short elem) {
      return indexOf(elem) >= 0;
   }

   /**
    * Search for the first occurence of the given argument.
    * @param element a short element.
    * @return the index of the first occurrence of the argument in this list;
    * <tt>-1</tt> if not found.
    */
   public int indexOf(short element) {
      for(int i = 0; i < size; i++) {
         if(element == arr[i]) {
            return i;
         }
      }

      return -1;
   }

   /**
    * Return the index of the last occurrence of the specified short in this
    * list.
    * @param element the desired element.
    * @return the index of the last occurrence of the specified short in this
    * list; if the short is not found.
    */
   public int lastIndexOf(short element) {
      for(int i = size - 1; i >= 0; i--) {
         if(element == arr[i]) {
            return i;
         }
      }

      return -1;
   }

   /**
    * Clone this object.
    * @return a clone of this <tt>XShortList</tt> instance.
    */
   @Override
   public Object clone() {
      try {
         XShortList v = (XShortList) super.clone();
         v.arr = new short[size];
         System.arraycopy(arr, 0, v.arr, 0, size);
         return v;
      }
      catch(Exception ex) {
         LOG.error("Failed to clone XShortList", ex);
      }

      return null;
   }

   /**
    * Return an array containing all of the elements in this list.
    * @return an array containing all of the elements in this list.
    */
   public short[] toArray() {
      short[] result = new short[size];
      System.arraycopy(arr, 0, result, 0, size);
      return result;
   }

   /**
    * Get the array.
    * @return the array.
    */
   public short[] getArray() {
      return arr;
   }

   /**
    * Return the element at the specified position in this list.
    * @param index index of element to return.
    * @return the element at the specified position in this list.
    */
   public short get(int index) {
      return arr[index];
   }

   /**
    * Replace the element at the specified position in this list with
    * the specified element.
    * @param index index of element to replace.
    * @param element element to be stored at the specified position.
    * @return the element previously at the specified position.
    */
   public short set(int index, short element) {
      short oldValue = arr[index];
      arr[index] = element;
      return oldValue;
   }

   /**
    * Append the specified element to the end of this list.
    * @param element element to be appended to this list.
    * @return <tt>true</tt>.
    */
   public boolean add(short element) {
      ensureCapacity(size + 1);
      arr[size++] = element;
      return true;
   }

   /**
    * Insert the specified element at the specified position in this list.
    * @param index index at which the specified element is to be inserted.
    * @param element element to be inserted.
    */
   public void add(int index, short element) {
      ensureCapacity(size + 1);
      System.arraycopy(arr, index, arr, index + 1, size - index);
      arr[index] = element;
      size++;
   }

   /**
    * Remove an element.
    * @param element the specified element.
    * @return <tt>true</tt> if removed, <tt>false</tt> otherwise.
    */
   public boolean removeElement(short element) {
      for(int i = 0; i < size; i++) {
         if(arr[i] == element) {
            remove(i);
            return true;
         }
      }

      return false;
   }

   /**
    * Remove the element at the specified position in this list.
    * @param index the index of the element to removed.
    * @return the element that was removed from the list.
    */
   public short remove(int index) {
      short oldValue = arr[index];
      int numMoved = size - index - 1;

      if(numMoved > 0) {
         System.arraycopy(arr, index + 1, arr, index, numMoved);
      }

      size--;
      return oldValue;
   }

   /**
    * Remove all of the elements from this list.
    */
   public void clear() {
      size = 0;
   }

   /**
    * Remove from this List all of the elements whose index is between
    * fromIndex, inclusive and toIndex, exclusive.
    * @param fromIndex index of first element to be removed.
    * @param toIndex index after last element to be removed.
    */
   protected void removeRange(int fromIndex, int toIndex) {
      int numMoved = size - toIndex;
      System.arraycopy(arr, toIndex, arr, fromIndex, numMoved);
      size = size - (toIndex - fromIndex);
   }

   protected short[] arr;
   protected int size;
   protected int capacity;

   private static final Logger LOG =
      LoggerFactory.getLogger(XShortList.class);
}