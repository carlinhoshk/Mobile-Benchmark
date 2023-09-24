import cv2
import time

# Carrega uma imagem
image = cv2.imread('sample_image.jpg')

# Inicia a contagem do tempo
start_time = time.time()

# Aplica um filtro de desfoque gaussiano Ã  imagem
blurred_image = cv2.GaussianBlur(image, (5, 5), 0)

# Salva a imagem processada
cv2.imwrite('blurred_image.jpg', blurred_image)

# Calcula o tempo decorrido
end_time = time.time()

elapsed_time_ms = (end_time - start_time) * 1000

# Exibe o tempo decorrido no terminal
print(f"Tempo decorrido: {end_time}")
print("---------------------------")
print(f"Tempo decorrido: {elapsed_time_ms} milissegundos")
print("----------------------------")