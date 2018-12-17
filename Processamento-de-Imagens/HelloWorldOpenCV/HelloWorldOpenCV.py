import cv2
 
image = cv2.imread("images\lena.jpg")
cv2.imshow("Over the Clouds", image)

cv2.waitKey(0)
cv2.destroyAllWindows()
