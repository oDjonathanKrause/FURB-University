import numpy as np
import cv2


SENSITIVITY_VALUE = 20

cap = cv2.VideoCapture(0)

while True:
    retval, frame1 = cap.read()
    gray_frame_1 = cv2.cvtColor(frame1, cv2.COLOR_BGR2GRAY)

    retval, frame2 = cap.read()
    gray_frame_2 = cv2.cvtColor(frame2, cv2.COLOR_BGR2GRAY)

    diff_img = cv2.absdiff(gray_frame_1, gray_frame_2)
    ret, threshold_result = cv2.threshold(diff_img, SENSITIVITY_VALUE, 255, cv2.THRESH_BINARY)
    blur_result = cv2.blur(threshold_result, (10, 10))

    ret2, threshold_result_2 = cv2.threshold(blur_result, SENSITIVITY_VALUE, 255, cv2.THRESH_BINARY)

    contours = cv2.findContours(threshold_result_2.copy(),cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)

    circles = cv2.HoughCircles(threshold_result_2.copy(), cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=0, maxRadius=0)
    if circles is not None:
        for i in circles[0, :]:
            cv2.circle(frame1, (i[0], i[1]), 2, (255, 100, 0), 100)

    try:
        for cnt in contours:
            (x, y, w, h) = cv2.boundingRect(cnt)
            cv2.rectangle(frame1, (x, y), (x + w, y + h), (0, 255, 100), 2)
    except:
        print()

    cv2.imshow("Frame", frame1)

    key = cv2.waitKey(1)
    if key == 27:
        break

cap.release()
cv2.destroyAllWindows()