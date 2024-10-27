# 번역과 이미지 생성 api호출등의 유틸리티 기능을 담당함

from transformers import pipeline
import requests

#hugging face 모델 설정
translator_pipeline = pipeline("translation", model="Helsinki-NLP/opus-mt-ko-en")

#hugging face api토큰과 url
API_URL = "https://api-inference.huggingface.co/models/black-forest-labs/FLUX.1-dev"
token = "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"
headers = {}

def translate_with_pipeline(text: str) -> str:
    #번역처리
    result = translator_pipeline(text)
    translated_text = result[0]['translation_text']
    return translated_text

def query_image_generation(translated_text: str) -> bytes:
    #이미지 생성 API호출
    response = requests.post(API_URL, headers=headers, json={"inputs":translated_text})
    return response.content if response.status_code == 200 else None

