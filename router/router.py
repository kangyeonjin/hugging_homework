from fastapi import APIRouter, HTTPException
from models.model import TranslationRequest
from services.service import translate_text, generate_image

router = APIRouter()

#번역 api엔드포인트
@router.post("/translate")
async def translate(request: TranslationRequest):
    translate_text = translate_text(request.text)
    return {"translate_text": translate_text}

#이미지생성 api엔드포인트
@router.post("/generate-image")
async def generate_image_from_text(request: TranslationRequest):
    image_content = generate_image(request.text)
    if not image_content:
        raise HTTPException(status_code=500, detail="Image generation failed")
    return {"image_content": image_content}

