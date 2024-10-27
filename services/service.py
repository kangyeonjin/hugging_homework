#번역과 이미지 생성작업을수행하는 함수정의

from utils.utils import translate_with_pipeline, query_image_generation

def translate_text(text: str) -> str:
    #파이프라인을 사용하여 텍스트 번역
    translate_text = translate_with_pipeline(text)
    return translate_text

def generate_image(text: str) -> bytes:
    #이미지 생성api를 호출하여 결과반환
    translate_text = translate_text(text)
    image_content = query_image_generation(translate_text)
    return image_content